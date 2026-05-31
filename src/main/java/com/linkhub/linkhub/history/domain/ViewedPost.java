package com.linkhub.linkhub.history.domain;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ViewedPost {

    private Long id;
    private Long userId;
    private Long postId;
    private Instant viewedAt;

    private ViewedPost(Long userId, Long postId, Instant viewedAt) {
        this.userId = userId;
        this.postId = postId;
        this.viewedAt = viewedAt;
    }

    public static ViewedPost create(Long userId, Long postId, Instant now) {
        if (userId == null || postId == null || now == null) {
            throw new IllegalArgumentException("Fields must not be null");
        }
        return new ViewedPost(userId, postId, now);
    }

    public static ViewedPost reconstitute(Long id, Long userId, Long postId, Instant viewedAt) {
        if (userId == null || postId == null || viewedAt == null) {
            throw new IllegalArgumentException("Fields must not be null");
        }
        ViewedPost vp = new ViewedPost(userId, postId, viewedAt);
        vp.id = id;
        return vp;
    }

}