package com.linkhub.linkhub.history.application.dto;

import java.time.Instant;

public record AddToHistoryResult (
        Long userId,
        Long postId,
        Instant viewedAt
){
}
