package com.linkhub.linkhub.reactions.api.dto;

import com.linkhub.linkhub.reactions.domain.ReactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReactionRequest(
        @NotNull Long userId,
        @NotNull Long postId,
        @NotNull ReactionType reactionType
) {
}
