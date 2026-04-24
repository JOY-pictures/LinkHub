package com.linkhub.linkhub.content.api.dto;

public record PostResponse(
        Long id,
        Long authorId,
        String text,
        String createdAt,
        String modeName
) {}
