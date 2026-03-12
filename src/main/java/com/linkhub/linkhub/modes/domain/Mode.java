package com.linkhub.linkhub.modes.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "modes")
@Getter
public class Mode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    protected Mode() {
        //JPA
    }

    public Mode(String name) {
        this.name = name;
    }

    public static Mode of(String name) {
        switch (name.toLowerCase()) {
            case "learning":
            case "fun":
            case "calm":
                return new Mode(name);
            default:
                throw new IllegalArgumentException("Unknown mode: " + name);
        }
    }
}
