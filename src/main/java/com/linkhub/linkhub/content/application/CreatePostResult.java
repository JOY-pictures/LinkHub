package com.linkhub.linkhub.content.application;

import java.time.Instant;

public record CreatePostResult(
        Long postId,
        Instant createdAt
) {}
