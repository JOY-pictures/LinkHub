package com.linkhub.linkhub.reactions.application.model;

public record PostReactionSummary (
        Long postId,
        long calmCount,
        long usefulCount,
        long funnyCount,
        long inspiringCount
){}
