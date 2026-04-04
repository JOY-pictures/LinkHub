package com.linkhub.linkhub.user.application.dto;

public record CreateUserCommand (
        String username,
        String displayName
){}
