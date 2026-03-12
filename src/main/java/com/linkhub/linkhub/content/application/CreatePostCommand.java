package com.linkhub.linkhub.content.application;

public record CreatePostCommand(
        String authorId,
        String text
) {}
