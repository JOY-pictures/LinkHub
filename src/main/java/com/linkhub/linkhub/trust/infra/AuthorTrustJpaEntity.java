package com.linkhub.linkhub.trust.infra;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "author_trust")
@Getter
public class AuthorTrustJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id", unique = true, nullable = false)
    private Long authorId;

    @Column(name = "trust_score", nullable = false)
    private double trustScore;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected AuthorTrustJpaEntity() {}

    public AuthorTrustJpaEntity(Long id, Long authorId, double trustScore, Instant updatedAt) {
        this.id = id;
        this.authorId = authorId;
        this.trustScore = trustScore;
        this.updatedAt = updatedAt;
    }
}
