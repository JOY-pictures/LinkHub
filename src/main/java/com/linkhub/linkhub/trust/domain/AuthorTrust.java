package com.linkhub.linkhub.trust.domain;

import lombok.Getter;

import java.time.Instant;

@Getter
public class AuthorTrust {

    private Long id;
    private Long authorId;
    private double trustScore;
    private Instant updatedAt;

    private AuthorTrust(Long authorId, double trustScore, Instant updatedAt) {
        this.authorId = authorId;
        this.trustScore = trustScore;
        this.updatedAt = updatedAt;
    }

    public static AuthorTrust createDefault(Long authorId, Instant now) {
        if (authorId == null || now == null) {
            throw new IllegalArgumentException("Fields must not be null");
        }
        return new AuthorTrust(authorId, 1.0, now);
    }

    public static AuthorTrust reconstitute(Long id, Long authorId, double trustScore, Instant updatedAt) {
        if (authorId == null || updatedAt == null) {
            throw new IllegalArgumentException("Fields must not be null");
        }
        AuthorTrust authorTrust = new AuthorTrust(authorId, trustScore, updatedAt);
        authorTrust.id = id;
        return authorTrust;
    }

    public void penalizeOrReward(boolean modeMatched, Instant now) {
        this.updatedAt = now;
        if (modeMatched) {
            this.trustScore = Math.min(1.0, this.trustScore + 0.02);
        } else {
            this.trustScore = Math.max(0, this.trustScore - 0.05);
        }
    }
}
