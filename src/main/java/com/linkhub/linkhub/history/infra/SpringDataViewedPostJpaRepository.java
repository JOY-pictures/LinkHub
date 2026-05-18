package com.linkhub.linkhub.history.infra;

import com.linkhub.linkhub.history.domain.ViewedPost;
import com.linkhub.linkhub.reactions.infra.ReactionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataViewedPostJpaRepository extends JpaRepository<ViewedPostEntity, Long> {
    Optional<ViewedPostEntity> findByUserIdAndPostId(Long userId, Long postId);
}
