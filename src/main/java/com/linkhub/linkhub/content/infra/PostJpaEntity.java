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

    @Column(name = "author_id", nullable = false, length = 64)
    private String authorId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected PostJpaEntity() {}

    public PostJpaEntity(String authorId, Instant createdAt) {
        this.authorId = authorId;
        this.createdAt = createdAt;
    }
}
