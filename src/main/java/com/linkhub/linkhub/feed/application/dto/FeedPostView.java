package com.linkhub.linkhub.feed.application.dto;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import com.linkhub.linkhub.reactions.domain.ReactionType;

import java.time.LocalDateTime;

public record FeedPostView (
        Long postId,
        Long authorId,
        String text,
        Long modeId,
        String modeName,
        LocalDateTime createdAt,
        String reason,
        PostReactionSummary reactions,
        ReactionType myReaction
) {}
