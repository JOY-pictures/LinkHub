package com.linkhub.linkhub.reactions.domain;

import java.time.Instant;

public record ReactionView (
        Long id,
        Long userId,
        Long postId,
        ReactionType reactionType,
        Instant createdAt
) {}
