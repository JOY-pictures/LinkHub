package com.linkhub.linkhub.users.infra;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 64, unique = true)
    private String username;

    @Column(name = "display_name", nullable = false, length = 64)
    private String displayName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected UserJpaEntity() {}

    public UserJpaEntity(String username, String displayName, Instant createdAt) {
        this.username = username.trim();
        this.displayName = displayName.trim();
        this.createdAt = createdAt;
    }

    public UserJpaEntity(Long id, String username, String displayName, Instant createdAt) {
        this.id = id;
        this.username = username.trim();
        this.displayName = displayName.trim();
        this.createdAt = createdAt;
    }
}
