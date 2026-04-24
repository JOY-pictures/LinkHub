package com.linkhub.linkhub.feed.application.dto;

import java.time.LocalDateTime;

public record FeedPostView (
        Long postId,
        Long authorId,
        String text,
        LocalDateTime createdAt,
        String reason
) {}
