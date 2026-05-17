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

    public TextPostJpaEntity(Long authorId, Instant createdAt, String text, Long modeId) {
        super(null, authorId, createdAt, modeId, null, false);
        this.text = text;
    }

    public TextPostJpaEntity(Long id, Long authorId, Instant createdAt, String text, Long modeId, Long communityModeId, boolean modeLocked) {
        super(id, authorId, createdAt, modeId, communityModeId, modeLocked);
        this.text = text;
    }
}