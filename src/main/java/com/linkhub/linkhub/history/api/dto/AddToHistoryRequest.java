package com.linkhub.linkhub.history.api.dto;

import jakarta.validation.constraints.NotNull;

public record AddToHistoryRequest (
        @NotNull Long userId,
        @NotNull Long postId
) {
}
