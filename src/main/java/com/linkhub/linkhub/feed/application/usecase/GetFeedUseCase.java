package com.linkhub.linkhub.feed.application.usecase;

import com.linkhub.linkhub.content.application.model.PostSummary;
import com.linkhub.linkhub.content.application.port.PostSortingPort;
import com.linkhub.linkhub.content.domain.PostContent;
import com.linkhub.linkhub.content.domain.TextContent;
import com.linkhub.linkhub.feed.application.dto.FeedPostView;
import com.linkhub.linkhub.feed.application.dto.GetFeedCommand;

import com.linkhub.linkhub.feed.application.ranking.PostRankingScorer;
import com.linkhub.linkhub.history.application.port.HistoryInformationPort;
import com.linkhub.linkhub.modes.application.model.ModeSummary;
import com.linkhub.linkhub.modes.application.port.ModeInformationPort;
import com.linkhub.linkhub.modes.application.port.UserModeInformationPort;
import com.linkhub.linkhub.reactions.domain.ReactionView;
import com.linkhub.linkhub.reactions.application.port.ReactionSummaryPort;
import com.linkhub.linkhub.reactions.application.port.UserReactionPort;
import com.linkhub.linkhub.reactions.domain.PostReactionSummary;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import com.linkhub.linkhub.trust.application.port.AuthorTrustInformationPort;
import com.linkhub.linkhub.users.application.exception.UserNotFoundException;
import com.linkhub.linkhub.users.application.port.UserInformationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetFeedUseCase {

    private final PostSortingPort postSortingPort;
    private final UserInformationPort userInformationPort;
    private final UserModeInformationPort userModeInformationPort;
    private final ModeInformationPort modeInformationPort;
    private final ReactionSummaryPort reactionSummaryPort;
    private final UserReactionPort userReactionPort;
    private final List<PostRankingScorer> scorers;
    private final HistoryInformationPort historyInformationPort;
    private final AuthorTrustInformationPort authorTrustInformationPort;

    @Transactional(readOnly = true)
    public List<FeedPostView> getFeed(GetFeedCommand command) {
        if (!userInformationPort.existsById(command.userId())) {
            throw new UserNotFoundException(command.userId());
        }

        int limit = normalizeLimit(command.limit());
        int candidateLimit = calculateCandidateLimit(limit);

        ModeSummary userMode = userModeInformationPort.findModeByUserId(command.userId());

        PostRankingScorer scorer = selectScorer(userMode.modeName());

        Set<Long> viewedPostIds = new HashSet<>(historyInformationPort.getPostIdsByUserId(command.userId()));

        List<PostSummary> rawCandidates = postSortingPort.findPostsByModeIdWithLimit(userMode.modeId(), candidateLimit);

        List<PostSummary> filteredCandidates = rawCandidates.stream()
                .filter(post -> !viewedPostIds.contains(post.id()))
                .toList();

        List<Long> postIds = filteredCandidates.stream()
                .map(PostSummary::id)
                .toList();

        Set<Long> authorIds = filteredCandidates.stream()
                .map(PostSummary::authorId)
                .collect(Collectors.toSet());


        Set<Long> modeIds = filteredCandidates.stream()
                .map(PostSummary::modeId)
                .collect(Collectors.toSet());

        Map<Long, String> modeNameMap = modeInformationPort.findModeNameByIds(modeIds);


        Map<Long, Double> authorTrustMap = authorTrustInformationPort.getTrustScoresByAuthorIds(authorIds);

        Map<Long, PostReactionSummary> reactionSummaryMap = reactionSummaryPort.getReactionSummariesByPostIds(postIds);

        Map<Long, Double> postScoreMap = filteredCandidates.stream()
                .collect(Collectors.toMap(
                        PostSummary::id,
                        post -> {
                            PostReactionSummary reactions = reactionSummaryMap.get(post.id());

                            double baseScore = scorer.calculateScore(reactions);
                            double trustScore = authorTrustMap.getOrDefault(post.authorId(), 1.0);
                            return baseScore * trustScore;
                }));

        List<PostSummary> sortedCandidates = filteredCandidates.stream()
                .sorted(Comparator.comparingDouble((PostSummary p) -> postScoreMap.get(p.id())).reversed())
                .toList();

        List<PostSummary> diverseCandidates = selectWithAuthorDiversity(sortedCandidates, limit);


        Map<Long, ReactionType> userReactionsMap = userReactionPort.getReactionsByUserIdAndPostIds(command.userId(),
                diverseCandidates.stream().map(PostSummary::id).toList());

        return diverseCandidates.stream()
                .map(post -> {
                    double finalScore = postScoreMap.get(post.id());

                    boolean isRanked = finalScore > 0;

                    String text = extractText(post.content());

                    String modeName = modeNameMap.get(post.modeId());

                    ReactionType userReaction = userReactionsMap.get(post.id());


                    return new FeedPostView(
                            post.id(),
                            post.authorId(),
                            text,
                            post.modeId(),
                            modeName,
                            LocalDateTime.ofInstant(post.createdAt(), ZoneId.systemDefault()),
                            explain(modeName, limit, isRanked),
                            reactionSummaryMap.get(post.id()),
                            userReaction
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

    private String explain (String modeName, int limit, boolean ranked) {
        String base = "Пост показан, потому что он относится к режиму: " + modeName + ".";
        if (ranked) {
            base += " Алгоритм ранжирования поднял его выше на основе реакций сообщества.";
        }
        return base + " Лимит ленты: " + limit;


    }

    private int calculateCandidateLimit(int limit) {
        return Math.min(limit * 5, 300);
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

    private PostRankingScorer selectScorer(String modeName) {
        return scorers.stream()
                .filter(s -> s.supports(modeName))
                .findFirst()
                .orElse(null);
    }
}