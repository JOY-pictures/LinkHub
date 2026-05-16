package com.linkhub.linkhub.content.infra;

import com.linkhub.linkhub.content.domain.PostContent;
import com.linkhub.linkhub.content.domain.PostType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "post_type")
@Getter
public abstract class PostJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "mode_id", nullable = false)
    private Long modeId;

    @Column(name = "community_mode_id")
    private Long communityModeId;

    @Column(name = "mode_locked", nullable = false)
    private boolean modeLocked = false;

    protected PostJpaEntity() {}

    public PostJpaEntity(Long authorId, Instant createdAt, Long modeId) {
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.modeId = modeId;
    }
}
