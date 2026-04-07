package com.linkhub.linkhub.reactions.infra;

import com.linkhub.linkhub.reactions.domain.Reaction;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface SpringDataReactionRepository extends JpaRepository<ReactionJpaEntity, Long> {
    Optional<ReactionJpaEntity> findByUserIdAndPostId(Long userId, Long postId);
    List<ReactionJpaEntity> findByPostId(Long postId);
    long countByPostIdAndReactionType(Long postId, ReactionType reactionType);
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    boolean existsById(Long id);

}
