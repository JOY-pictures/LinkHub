package com.linkhub.linkhub.users.application.dto;

import java.time.Instant;

public record GetUserResult (
    String username,
    String displayName,
    Instant createdAt
){}
