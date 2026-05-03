package com.linkhub.linkhub.feed.application.usecase;

import com.linkhub.linkhub.content.application.model.PostSummary;
import com.linkhub.linkhub.content.application.port.PostSortingPort;
import com.linkhub.linkhub.content.domain.PostContent;
import com.linkhub.linkhub.content.domain.TextContent;
import com.linkhub.linkhub.feed.application.dto.FeedPostView;
import com.linkhub.linkhub.feed.application.dto.GetFeedCommand;

import com.linkhub.linkhub.modes.application.model.ModeSummary;
import com.linkhub.linkhub.modes.application.port.ModeInformationPort;
import com.linkhub.linkhub.modes.application.port.UserModeInformationPort;
import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import com.linkhub.linkhub.reactions.application.port.ReactionSummaryPort;
import com.linkhub.linkhub.users.application.exception.UserNotFoundException;
import com.linkhub.linkhub.users.application.port.UserInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeedUseCase {

    private final PostSortingPort postSortingPort;
    private final UserInformationPort userInformationPort;
    private final UserModeInformationPort userModeInformationPort;
    private final ModeInformationPort modeInformationPort;
    private final ReactionSummaryPort reactionSummaryPort;

    public List<FeedPostView> getFeed(GetFeedCommand command) {
        if (!userInformationPort.existsById(command.userId())) {
            throw new UserNotFoundException(command.userId());
        }

        int limit = normalizeLimit(command.limit());
        int candidateLimit = calculateCandidateLimit(limit);

        ModeSummary userMode = userModeInformationPort.findModeByUserId(command.userId());

        List<PostSummary> summary = postSortingPort.findPostsByModeWithLimit(userMode.modeName(), candidateLimit);

        return selectWithAuthorDiversity(summary, limit).stream().map(postSummary -> {
                    String text = extractText(postSummary.content());
                    String modeName = modeInformationPort.findModeById(postSummary.modeId()).modeName();
                    PostReactionSummary reactions = reactionSummaryPort.count(postSummary.id());

                    return new FeedPostView(
                            postSummary.id(),
                            postSummary.authorId(),
                            text,
                            postSummary.modeId(),
                            modeName,
                            LocalDateTime.ofInstant(postSummary.createdAt(), ZoneId.systemDefault()),
                            explain(modeName, limit),
                            reactions
                    );
                })
                .toList();
    }

    private int normalizeLimit(int limit) {
        if (limit <= 0) {
            return 20;
        }

        return Math.min(limit, 100);
    }

    private String extractText (PostContent content) {
        if (content instanceof TextContent tc) {
            return tc.getText();
        }
        throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
    }

    private String explain (String modeName, int limit) {
        return  "Пост показан, потому что он относится к режиму: " + modeName +
                ", который сейчас выбран у пользователя. " +
                "Он попал в выбранный лимит ленты: " + limit + " постов";
    }

    private int calculateCandidateLimit(int limit) {
        return Math.min(limit * 3, 100);
    }

    private List<PostSummary> selectWithAuthorDiversity (List<PostSummary> summaries, int limit) {
        List<PostSummary> resultSummary = new ArrayList<PostSummary>();
        List<PostSummary> postpone = new ArrayList<PostSummary>();
        int maxInRaw = 2;
        Long lastAuthor = null;
        int count = 0;
        for (PostSummary post: summaries) {
            if (resultSummary.size() >= limit) {
                break;
            }

            if (post.authorId().equals(lastAuthor)) {
                if (count < maxInRaw) {
                    resultSummary.add(post);
                    count += 1;
                    continue;
                } else {
                    postpone.add(post);
                }
            } else {
                lastAuthor = post.authorId();
                count = 1;
                resultSummary.add(post);
            }
        }

        if (resultSummary.size() < limit) {
            int need = limit - resultSummary.size();
            int countToAdd = Math.min(need, postpone.size());
            resultSummary.addAll(postpone.subList(0, countToAdd));
        }

        return resultSummary;
    }
}