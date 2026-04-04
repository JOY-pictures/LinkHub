package com.linkhub.linkhub.modes.infra;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "modes")
@Getter
public class ModeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    protected ModeJpaEntity() {}

    public ModeJpaEntity(String name) {
        this.name = name;
    }
}
