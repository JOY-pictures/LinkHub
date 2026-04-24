package com.linkhub.linkhub.feed.application.usecase;

import com.linkhub.linkhub.content.application.port.PostInformationPort;
import com.linkhub.linkhub.feed.application.dto.FeedPostView;
import com.linkhub.linkhub.feed.application.dto.GetFeedCommand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeedUseCase {

    private final PostInformationPort

    public List<FeedPostView> getFeed(GetFeedCommand command) {
        int limit = normalizeLimit(command.limit());

        String modeName = feedUserModePort.getModeNameByUserId(command.userId());

        return feedPostProviderPort.findRecentPostByMode(modeName, limit);
    }

    private int normalizeLimit(int limit) {
        if (limit <= 0) {
            return 20;
        }

        return Math.min(limit, 100);
    }
}
