package com.linkhub.linkhub.user.application;

import java.time.Instant;

public record CreateUserResult (
        String username,
        String displayName,
        Instant createdAt
){}
