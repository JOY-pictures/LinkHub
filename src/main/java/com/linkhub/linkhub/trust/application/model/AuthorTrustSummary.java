package com.linkhub.linkhub.trust.application.model;

import java.time.Instant;

public record AuthorTrustSummary (
        Long authorId,
        double trustScore,
        Instant updatedAt
) {
}
