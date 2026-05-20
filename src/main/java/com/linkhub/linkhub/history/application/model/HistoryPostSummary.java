package com.linkhub.linkhub.history.application.model;

import java.time.Instant;

public record HistoryPostSummary(
        Long postId,
        Instant viewedAt
) {
}
