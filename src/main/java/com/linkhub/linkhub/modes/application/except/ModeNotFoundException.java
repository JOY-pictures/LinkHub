package com.linkhub.linkhub.modes.application.except;

public class ModeNotFoundException extends RuntimeException {
    public ModeNotFoundException(String modeName) {
        super("Mode not found by name: " + modeName);
    }
}
