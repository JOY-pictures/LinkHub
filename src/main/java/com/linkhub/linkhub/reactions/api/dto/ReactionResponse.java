package com.linkhub.linkhub.reactions.api.dto;

import com.linkhub.linkhub.reactions.domain.ReactionType;

import java.time.Instant;

public record ReactionResponse (
        Long id,
        Long userId,
        Long postId,
        ReactionType reactionType,
        Instant createdAt
) {}
