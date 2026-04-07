package com.linkhub.linkhub.users.application.model;

import java.time.Instant;

public record UserSummary(
        Long id,
        String username,
        String displayName,
        Instant createdAt
) {
}
