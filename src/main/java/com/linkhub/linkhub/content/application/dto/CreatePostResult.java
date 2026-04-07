package com.linkhub.linkhub.content.application.dto;

import java.time.Instant;

public record CreatePostResult(
        Long postId,
        Instant createdAt
) {}
