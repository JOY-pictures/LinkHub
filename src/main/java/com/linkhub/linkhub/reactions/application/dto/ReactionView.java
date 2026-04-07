package com.linkhub.linkhub.reactions.application.dto;

import com.linkhub.linkhub.reactions.domain.ReactionType;

import java.time.Instant;

public record ReactionView (
        Long id,
        Long userId,
        Long postId,
        ReactionType reactionType,
        Instant createdAt
) {}
