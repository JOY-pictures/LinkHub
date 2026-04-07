package com.linkhub.linkhub.content.application.dto;

public record PostView(
        Long id,
        Long authorId,
        String text,
        String createdAt
) {}
