package com.linkhub.linkhub.trust.application.dto;

import java.time.Instant;

public record TrustView(
        Long authorId,
        double trustScore,
        Instant updatedAt
) {
}
