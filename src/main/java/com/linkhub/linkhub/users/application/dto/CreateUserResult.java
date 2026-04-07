package com.linkhub.linkhub.users.application.dto;

import java.time.Instant;

public record CreateUserResult (
        Long id,
        String username,
        String displayName,
        Instant createdAt
){}
