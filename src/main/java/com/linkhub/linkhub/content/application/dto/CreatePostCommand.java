package com.linkhub.linkhub.content.application.dto;

public record CreatePostCommand(
        Long authorId,
        String text,
        String modeName
) {}
