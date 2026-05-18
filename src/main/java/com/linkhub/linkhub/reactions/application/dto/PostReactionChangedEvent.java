package com.linkhub.linkhub.reactions.application.dto;

import com.linkhub.linkhub.reactions.domain.ReactionType;

import java.time.Instant;

public record PostReactionChangedEvent(
        Long postId,
        Long userId,
        ReactionType reactionType,
        Instant changedAt
) {
}
