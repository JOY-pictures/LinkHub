package com.linkhub.linkhub.content.application.except;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super("Post not found: ID - " + postId);
    }
}
