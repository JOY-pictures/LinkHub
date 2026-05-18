package com.linkhub.linkhub.history.infra;

import com.linkhub.linkhub.history.domain.ViewedPost;
import com.linkhub.linkhub.history.domain.ViewedPostRepository;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(
        name = "viewed_posts", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "post_id"})
)
@Getter
public class ViewedPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "viewed_at", nullable = false)
    private Instant viewedAt;

    protected ViewedPostEntity() {}

    public ViewedPostEntity(Long id, Long userId, Long postId, Instant viewedAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.viewedAt = viewedAt;
    }
}
