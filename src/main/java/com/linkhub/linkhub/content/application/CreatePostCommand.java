package com.linkhub.linkhub.content.application;

public record CreatePostCommand(
        Long authorId,
        String text
) {}
