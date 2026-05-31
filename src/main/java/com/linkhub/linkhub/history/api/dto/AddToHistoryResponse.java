package com.linkhub.linkhub.history.api.dto;

import java.time.Instant;

public record AddToHistoryResponse (
        Long userId,
        Long postId,
        Instant viewedAt
) {
}
