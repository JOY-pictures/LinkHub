package com.linkhub.linkhub.user.api.dto;

import java.time.Instant;

public record CreateUserResponse(
        String username,
        String displayName,
        String createdAt
) {}
