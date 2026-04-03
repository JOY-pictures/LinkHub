package com.linkhub.linkhub.content.infra;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "text_content")
@DiscriminatorValue("TEXT")
@Getter
public class TextPostJpaEntity extends PostJpaEntity{

    @Column(name = "text", nullable = false, length = 4000)
    private String text;

    protected TextPostJpaEntity() {}

    public TextPostJpaEntity(String authorId, Instant createdAt, String text) {
        super(authorId, createdAt);
        this.text = text;
    }
}