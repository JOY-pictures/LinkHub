package com.linkhub.linkhub.modes.infra;

import com.linkhub.linkhub.modes.domain.Mode;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "user_modes")
@Getter
public class UserModeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mode_id", nullable = false)
    private ModeJpaEntity mode;

    protected UserModeJpaEntity() {}

    public UserModeJpaEntity(String userId, ModeJpaEntity mode) {
        this.userId = userId;
        this.mode = mode;
    }

    public UserModeJpaEntity(Long id, String userId, ModeJpaEntity mode) {
        this.id = id;
        this.userId = userId;
        this.mode = mode;
    }
}
