package com.linkhub.linkhub.content.application;

public record PostView(
        Long id,
        Long authorId,
        String text,
        String createdAt
) {}
