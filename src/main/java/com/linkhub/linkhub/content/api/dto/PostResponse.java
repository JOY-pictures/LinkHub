package com.linkhub.linkhub.content.api.dto;

public record PostResponse(
        Long id,
        String authorId,
        String text,
        String createdAt
) {}
