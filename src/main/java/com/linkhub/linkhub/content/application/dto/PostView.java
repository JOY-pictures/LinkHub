package com.linkhub.linkhub.content.application.dto;

import com.linkhub.linkhub.content.domain.PostContent;

import java.time.Instant;

public record PostView(
        Long id,
        Long authorId,
        PostContent content,
        Instant createdAt
) {}
