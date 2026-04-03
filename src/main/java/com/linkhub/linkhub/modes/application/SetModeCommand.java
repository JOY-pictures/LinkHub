package com.linkhub.linkhub.modes.application;

public record SetModeCommand(
        String userId,
        String modeName
){}
