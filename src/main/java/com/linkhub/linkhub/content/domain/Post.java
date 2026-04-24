package com.linkhub.linkhub.content.domain;


import lombok.Getter;

import java.time.Instant;

@Getter
public class Post {

    private Long id;

    private Long authorId;

    private Instant createdAt;

    private PostType postType;

    private PostContent content;

    private Long modeId;

    private Post(Long authorId, Instant createdAt, PostType postType, PostContent content, Long modeId) {
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.postType = postType;
        this.content = content;
        this.modeId = modeId;
    }

    public static Post create(Long authorId, Instant createdAt, PostType postType, PostContent content, Long modeId) {
        if (authorId == null) {
            throw new IllegalArgumentException("authorId must not be blank");
        }
        if (postType == null) {
            throw new IllegalArgumentException("post type must not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content must not be null");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt must not be null");
        }
        if (modeId == null) {
            throw new IllegalArgumentException("modelId must not be null");
        }
        return new Post(authorId, createdAt, postType, content, modeId);
    }

    public static Post reconstitute(Long id, Long authorId, PostType postType, Instant createdAt, PostContent content, Long modeId) {
        if (authorId == null) {
            throw new IllegalArgumentException("authorId must not be blank");
        }
        if (postType == null) {
            throw new IllegalArgumentException("post type must not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content must not be null");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt must not be null");
        }
        if (modeId == null) {
            throw new IllegalArgumentException("modelId must not be null");
        }
        Post post = new Post(authorId, createdAt, postType, content, modeId);
        post.id = id;
        return post;
    }
}
