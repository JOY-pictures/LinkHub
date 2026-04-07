package com.linkhub.linkhub.users.domain;

import lombok.Getter;

import java.time.Instant;

@Getter
public class User {

    private Long id;

    private String username;

    private String displayName;

    private Instant createdAt;

    private User(String username, String displayName, Instant createdAt) {
        this.username = username;
        this.displayName = displayName;
        this.createdAt = createdAt;
    }

    public static User create(String username, String displayName, Instant now) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("displayName must not be blank");
        }
        if (now == null) {
            throw new IllegalArgumentException("createdAt must not be null");
        }
        return new User(username.trim(), displayName.trim(), now);
    }

    public static User reconstitute(Long id, String username, String displayName, Instant createdAt) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("displayName must not be blank");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt must not be null");
        }
        User user = new User(username, displayName, createdAt);
        user.id = id;
        return user;
    }
}