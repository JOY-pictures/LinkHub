package com.linkhub.linkhub.user.application;

import java.time.Instant;

public record GetUserResult (
    String username,
    String displayName,
    Instant createdAt
){}
