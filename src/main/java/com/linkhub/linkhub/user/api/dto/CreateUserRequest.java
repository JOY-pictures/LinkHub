package com.linkhub.linkhub.user.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest (
        @NotBlank @Size(max = 64) String username,
        @NotBlank @Size(max = 64) String displayName
){}