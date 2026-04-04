package com.linkhub.linkhub.modes.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Mode {

    private Long id;

    private String name;

    private Mode(String name) {
        this.name = name;
    }

    public static Mode create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Mode name must not be blank");
        }
        return new Mode(name.trim());
    }

    public static Mode reconstitute(Long id, String name) {
        Mode mode = new Mode(name);
        mode.id = id;
        return mode;
    }
}
