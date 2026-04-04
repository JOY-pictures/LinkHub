package com.linkhub.linkhub.modes.application;

public record SetModeCommand(
        Long userId,
        String modeName
){}
