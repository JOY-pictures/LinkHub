package com.linkhub.linkhub.user.api.dto;

import java.time.Instant;

public record CreateUserResponse(
        Long id,
        String username,
        String displayName,
        Instant createdAt
) {}
