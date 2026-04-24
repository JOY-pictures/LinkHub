package com.linkhub.linkhub.modes.application.except;

public class UserModeNotFoundException extends RuntimeException {
    public UserModeNotFoundException(Long userId) {
        super("Mode not found for user: " + userId);
    }
}
