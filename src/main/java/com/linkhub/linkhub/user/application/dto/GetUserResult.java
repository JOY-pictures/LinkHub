package com.linkhub.linkhub.user.application.dto;

import java.time.Instant;

public record GetUserResult (
    String username,
    String displayName,
    Instant createdAt
){}
