package com.linkhub.linkhub.feed.application.dto;

public record GetFeedCommand (
    Long userId,
    int limit
) {}
