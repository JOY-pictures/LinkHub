package com.linkhub.linkhub.content.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "posts")
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authorId", nullable = false, length = 64)
    private String authorId;

    @Column(name = "text", nullable = false, length = 4000)
    private String text;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected Post() {
        //JPA
    }

    private Post(String authorId, String text, Instant createdAt) {
        this.authorId = authorId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public static Post create(String authorId, String text, Instant now) {
        if (authorId == null || authorId.isBlank()) {
            throw new IllegalArgumentException("authorId must not be blank");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("text must not be blank");
        }
        if (text.length() > 4000) {
            throw new IllegalArgumentException("text is too long");
        }
        return new Post(authorId.trim(), text.trim(), now);
    }


}
