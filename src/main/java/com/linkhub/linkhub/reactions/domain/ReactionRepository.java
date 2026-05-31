package com.linkhub.linkhub.reactions.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReactionRepository {
    Reaction save(Reaction reaction);
    Optional<Reaction> findById (Long id);

    Optional<Reaction> findByUserIdAndPostId(Long userId, Long postId);
    List<Reaction> findByPostId(Long postId);
    void delete(Reaction reaction);
    long countByPostIdAndReactionType(Long postId, ReactionType reactionType);
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    boolean existsById(Long id);

    List<Reaction> findByPostIds(Collection<Long> postIds);

    PostReactionSummary findSummaryByPostId(Long postId);
    Map<Long, PostReactionSummary> findSummariesByPostIds(Collection<Long> postIds);

    Map<Long, ReactionType> findReactionsByUserIdAndPostIds(Long userId, Collection<Long> postIds);
}
