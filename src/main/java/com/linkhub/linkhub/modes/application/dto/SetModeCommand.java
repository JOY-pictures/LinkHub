package com.linkhub.linkhub.modes.application.dto;

public record SetModeCommand(
        Long userId,
        String modeName
){}
