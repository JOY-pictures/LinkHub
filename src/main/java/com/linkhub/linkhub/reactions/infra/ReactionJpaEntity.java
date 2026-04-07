package com.linkhub.linkhub.reactions.infra;

import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "reaction")
@Getter
public class ReactionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction_type", nullable = false)
    private ReactionType reactionType;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected ReactionJpaEntity() {}

    public ReactionJpaEntity(Long userId, Long postId, ReactionType reactionType, Instant createdAt) {
        this.userId = userId;
        this.postId = postId;
        this.reactionType = reactionType;
        this.createdAt = createdAt;
    }

    public ReactionJpaEntity(Long id, Long userId, Long postId, ReactionType reactionType, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.reactionType = reactionType;
        this.createdAt = createdAt;
    }
}
