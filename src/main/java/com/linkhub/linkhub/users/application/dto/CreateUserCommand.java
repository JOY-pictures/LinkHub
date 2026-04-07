package com.linkhub.linkhub.users.application.dto;

public record CreateUserCommand (
        String username,
        String displayName
){}
