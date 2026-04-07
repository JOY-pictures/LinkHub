package com.linkhub.linkhub.users.api.dto;

import java.time.Instant;

public record UserResponse(
    String username,
    String displayName,
    Instant createdAt
) {}
