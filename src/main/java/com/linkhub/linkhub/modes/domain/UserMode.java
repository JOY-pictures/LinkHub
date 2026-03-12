package com.linkhub.linkhub.modes.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_mode")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mode_id", nullable = false)
    private Mode mode;

    public UserMode(String userId, Mode mode) {
        this.userId = userId;
        this.mode = mode;
    }

    public void changeMode(Mode mode) {
        this.mode = mode;
    }
}
