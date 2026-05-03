package com.linkhub.linkhub.feed.api.dto;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;

import java.time.LocalDateTime;

public record FeedPostResponse (
        Long postId,
        Long authorId,
        String text,
        Long modeId,
        String modeName,
        LocalDateTime createdAt,
        String reason,
        PostReactionSummary reactions
) {}
