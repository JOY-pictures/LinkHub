package com.linkhub.linkhub.feed.application.usecase;

import com.linkhub.linkhub.content.application.port.PostInformationPort;
import com.linkhub.linkhub.content.application.port.PostSortingPort;
import com.linkhub.linkhub.content.domain.PostContent;
import com.linkhub.linkhub.content.domain.TextContent;
import com.linkhub.linkhub.feed.application.dto.FeedPostView;
import com.linkhub.linkhub.feed.application.dto.GetFeedCommand;

import com.linkhub.linkhub.modes.application.port.ModeInformationPort;
import com.linkhub.linkhub.modes.application.service.UserModeInformationService;
import com.linkhub.linkhub.users.application.exception.UserNotFoundException;
import com.linkhub.linkhub.users.application.port.UserInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeedUseCase {

    private final PostSortingPort postSortingPort;
    private final UserInformationPort userInformationPort;
    private final UserModeInformationService userModeInformationService;
    private final ModeInformationPort modeInformationPort;

    public List<FeedPostView> getFeed(GetFeedCommand command) {
        if (!userInformationPort.existsById(command.userId())) {
            throw new UserNotFoundException(command.userId());
        }

        int limit = normalizeLimit(command.limit());

        String userMode = userModeInformationService.findModeByUserId(command.userId()).modeName();

        return postSortingPort.findPostsByModeWithLimit(userMode, limit).
                stream().map(postSummary -> {
                    String text = extractText(postSummary.content());
                    String modeName = modeInformationPort.findModeById(postSummary.modeId()).modeName();
                    return new FeedPostView(
                            postSummary.id(),
                            postSummary.authorId(),
                            text,
                            postSummary.modeId(),
                            modeName,
                            LocalDateTime.ofInstant(postSummary.createdAt(), ZoneId.systemDefault()),
                            "Conforms to the mode"
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
}
