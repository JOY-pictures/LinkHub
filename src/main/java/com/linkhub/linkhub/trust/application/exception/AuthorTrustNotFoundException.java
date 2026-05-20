package com.linkhub.linkhub.trust.application.exception;

public class AuthorTrustNotFoundException extends RuntimeException {
    public AuthorTrustNotFoundException(Long authorId) {
        super("Author not found by ID: " + authorId);
    }
}
