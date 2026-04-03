package com.linkhub.linkhub.modes.api.dto;

import jakarta.validation.constraints.NotBlank;

public record UserModeRequest(
        @NotBlank String modeName
) {}