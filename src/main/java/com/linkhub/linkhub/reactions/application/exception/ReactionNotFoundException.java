package com.linkhub.linkhub.reactions.application.exception;

public class ReactionNotFoundException extends RuntimeException {
    public ReactionNotFoundException(Long userId, Long postId) {
        super("Reaction with such userId and postId not found: userId - " + userId + "; postId -" + postId);
    }
    public ReactionNotFoundException(Long id) {
        super("Reaction not found by Id: " + id);
    }
}
