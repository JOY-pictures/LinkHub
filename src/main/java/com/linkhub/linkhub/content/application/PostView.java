package com.linkhub.linkhub.content.application;

public record PostView(
        Long id,
        String authorId,
        String text,
        String createdAt
) {}
