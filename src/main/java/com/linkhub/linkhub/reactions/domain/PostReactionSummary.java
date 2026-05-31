package com.linkhub.linkhub.reactions.domain;

public record PostReactionSummary (
        Long postId,
        long totalCount,
        long calmCount,
        long usefulCount,
        long funnyCount,
        long inspiringCount
){}
