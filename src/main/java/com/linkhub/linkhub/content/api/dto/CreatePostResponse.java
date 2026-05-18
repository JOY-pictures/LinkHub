package com.linkhub.linkhub.content.api.dto;

import java.time.Instant;

public record CreatePostResponse(
        Long postId,
        Instant createdAt
) {}
