package com.linkhub.linkhub.reactions.infra;

import com.linkhub.linkhub.reactions.domain.Reaction;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import com.linkhub.linkhub.reactions.domain.ReactionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

interface SpringDataReactionRepository extends JpaRepository<ReactionJpaEntity, Long> {
    Optional<ReactionJpaEntity> findByUserIdAndPostId(Long userId, Long postId);
    List<ReactionJpaEntity> findByPostId(Long postId);
    long countByPostIdAndReactionType(Long postId, ReactionType reactionType);
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    boolean existsById(Long id);
    List<ReactionJpaEntity> findByPostIdIn(Collection<Long> postIds);

    @Query("SELECT r.reactionType, COUNT(r) FROM ReactionJpaEntity r " +
    "WHERE r.postId = :postId " +
    "GROUP BY r.reactionType")
    List<Object[]> countReactionsByPostId(@Param("postId") Long postId);

    @Query("SELECT r.postId, r.reactionType, COUNT(r) FROM ReactionJpaEntity r " +
    "WHERE r.postId in :postIds " +
    "GROUP BY r.postId, r.reactionType")
    List<Object[]> countReactionsByPostIds(@Param("postIds") Collection<Long> postIds);

    @Query("SELECT r.postId, r.reactionType FROM ReactionJpaEntity r " +
    "WHERE r.userId = :userId AND r.postId IN :postIds")
    List<Object[]> findReactionsByUserIdAndPostIds(Long userId, Collection<Long> postIds);
}
