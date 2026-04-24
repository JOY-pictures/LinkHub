package com.linkhub.linkhub.feed.application.dto;

import java.time.LocalDateTime;

public record FeedPostView (
        Long postId,
        Long authorId,
        String text,
        Long modeId,
        String modeName,
        LocalDateTime createdAt,
        String reason
) {}
