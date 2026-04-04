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

    private Post(Long authorId, Instant createdAt, PostType postType, PostContent content) {
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.postType = postType;
        this.content = content;
    }

    public static Post create(Long authorId, Instant now, PostType postType, PostContent content) {
        if (authorId == null) {
            throw new IllegalArgumentException("authorId must not be blank");
        }
        if (postType == null) {
            throw new IllegalArgumentException("post type must not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content must not be null");
        }
        if (now == null) {
            throw new IllegalArgumentException("createdAt must not be null");
        }
        return new Post(authorId, now, postType, content);
    }

    public static Post reconstitute(Long id, Long authorId, PostType postType, Instant createdAt, PostContent content) {
        Post post = new Post(authorId, createdAt, postType, content);
        post.id = id;
        return post;
    }
}
