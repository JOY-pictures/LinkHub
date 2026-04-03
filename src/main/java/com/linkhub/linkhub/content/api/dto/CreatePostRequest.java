package com.linkhub.linkhub.content.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotBlank @Size(max = 64) String authorId,
        @NotBlank @Size(max = 4000) String text
) {}