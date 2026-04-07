package com.linkhub.linkhub.reactions.application.dto;

import com.linkhub.linkhub.reactions.domain.ReactionType;

public record CreateReactionCommand(
        Long userId,
        Long postId,
        ReactionType reactionType
) {}
