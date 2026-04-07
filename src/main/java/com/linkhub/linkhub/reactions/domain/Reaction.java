package com.linkhub.linkhub.reactions.domain;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Reaction {

    private Long id;
    private Long userId;
    private Long postId;
    private ReactionType reactionType;
    private Instant createdAt;

    private Reaction(Long userId, Long postId, ReactionType reactionType, Instant createdAt) {
        this.userId = userId;
        this.postId = postId;
        this.reactionType = reactionType;
        this.createdAt = createdAt;
    }

    public void changeType(ReactionType reactionType) {
        if (reactionType == null) {
            throw new IllegalArgumentException("ReactionType must not be blank");
        }
        this.reactionType = reactionType;
    }

    public static Reaction create(Long userId, Long postId, ReactionType reactionType, Instant createdAt) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be blank");
        }
        if (postId == null) {
            throw new IllegalArgumentException("Post ID must not be blank");
        }
        if (reactionType == null) {
            throw new IllegalArgumentException("ReactionType must not be blank");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("CreatedAt must not be blank");
        }
        return new Reaction(userId, postId, reactionType, createdAt);
    }

    public static Reaction reconstitute(Long id, Long userId, Long postId, ReactionType reactionType, Instant createdAt) {
        if (id == null) {
            throw new IllegalArgumentException("Reaction ID must not be blank");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be blank");
        }
        if (postId == null) {
            throw new IllegalArgumentException("Post ID must not be blank");
        }
        if (reactionType == null) {
            throw new IllegalArgumentException("ReactionType must not be blank");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("CreatedAt must not be blank");
        }
        Reaction reaction = new Reaction(userId, postId, reactionType, createdAt);
        reaction.id = id;
        return reaction;
    }
}
