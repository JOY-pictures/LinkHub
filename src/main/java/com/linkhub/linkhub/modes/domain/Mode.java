package com.linkhub.linkhub.modes.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


    public Mode(String name) {
        this.name = name;
    }
}
