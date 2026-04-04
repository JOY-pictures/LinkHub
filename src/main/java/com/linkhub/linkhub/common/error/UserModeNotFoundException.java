package com.linkhub.linkhub.common.error;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class UserModeNotFoundException extends RuntimeException {
    public UserModeNotFoundException(Long userId) {
        super("Mode not found for user: " + userId);
    }
}
