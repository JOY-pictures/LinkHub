package com.linkhub.linkhub.feed.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FeedPostRequest (
        @NotNull @Positive Long userId,
        @NotNull @Positive int limit
) {}
