package com.linkhub.linkhub.modes.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Getter
public class UserMode {

    private Long id;

    private Long userId;

    private Mode mode;

    public UserMode(Long userId, Mode mode) {
        this.userId = userId;
        this.mode = mode;
    }

    public static UserMode create(Long userId, Mode mode) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be blank");
        }
        if (mode == null) {
            throw new IllegalArgumentException("Mode must not be null");
        }
        return new UserMode(userId, mode);
    }



    public static UserMode reconstitute(Long id, Long userId, Mode mode) {
        UserMode um = new UserMode(userId, mode);
        um.id = id;
        return um;
    }

    public void changeMode(Mode mode) {
        this.mode = mode;
    }
}
