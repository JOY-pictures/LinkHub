package com.linkhub.linkhub.user.application;

public record CreateUserCommand (
        String username,
        String displayName
){}
