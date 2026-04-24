package com.linkhub.linkhub.content.application.model;

import com.linkhub.linkhub.content.domain.PostContent;

import java.time.Instant;

public record PostSummary (
        Long id,
        Long authorId,
        PostContent content,
        Instant createdAt,
        Long modeId
){}
