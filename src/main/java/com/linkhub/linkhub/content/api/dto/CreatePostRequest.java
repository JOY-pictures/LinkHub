package com.linkhub.linkhub.content.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotNull @Positive Long authorId,
        @NotBlank @Size(max = 4000) String text
) {}