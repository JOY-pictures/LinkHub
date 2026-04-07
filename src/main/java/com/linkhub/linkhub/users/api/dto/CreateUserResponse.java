package com.linkhub.linkhub.users.api.dto;

import java.time.Instant;

public record CreateUserResponse(
        Long id,
        String username,
        String displayName,
        Instant createdAt
) {}
