package com.linkhub.linkhub.reactions.infra;

import com.linkhub.linkhub.reactions.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ReactionRepositoryJpaAdapter implements ReactionRepository {

    private final SpringDataReactionRepository jpa;

    @Override
    public Reaction save(Reaction reaction) {
        ReactionJpaEntity entity = toJpaEntity(reaction);
        ReactionJpaEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Reaction> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return jpa.existsById(id);
    }

    @Override
    public Optional<Reaction> findByUserIdAndPostId(Long userId, Long postId) {
        return jpa.findByUserIdAndPostId(userId, postId).
                map(this::toDomain);
    }

    @Override
    public List<Reaction> findByPostId(Long postId) {
        return jpa.findByPostId(postId).stream().
                map(this::toDomain).toList();
    }

    @Override
    public void delete(Reaction reaction) {
        ReactionJpaEntity entity = toJpaEntity(reaction);
        jpa.delete(entity);
    }

    @Override
    public long countByPostIdAndReactionType(Long postId, ReactionType type) {
        return jpa.countByPostIdAndReactionType(postId, type);
    }

    @Override
    public boolean existsByUserIdAndPostId(Long userId, Long postId) {
        return jpa.existsByUserIdAndPostId(userId, postId);
    }

    @Override
    public List<Reaction> findByPostIds(Collection<Long> postIds) {
        return jpa.findByPostIdIn(postIds).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public PostReactionSummary findSummaryByPostId(Long postId) {
        List<Object[]> rows = jpa.countReactionsByPostId(postId);

        long calm = 0; long useful = 0; long funny = 0; long inspiring = 0; long total = 0;

        for (Object[] row: rows) {
            ReactionType reactionType = (ReactionType) row[0];
            long count = (long) row[1];
            total += count;
            switch (reactionType) {
                case CALM -> calm = count;
                case USEFUL -> useful = count;
                case FUNNY -> funny = count;
                case INSPIRING -> inspiring = count;
            }
        }
        return new PostReactionSummary(postId, total, calm, useful, funny, inspiring);
    }

    @Override
    public Map<Long, PostReactionSummary> findSummariesByPostIds(Collection<Long> postIds) {
        if (postIds == null || postIds.isEmpty()) {
            return Collections.emptyMap();
        }

        List<Object[]> rows = jpa.countReactionsByPostIds(postIds);
        Map<Long, Map<ReactionType, Long>> countsByPostMap = new HashMap<>();

        for (Object[] row: rows) {
            Long postId = (Long) row[0];
            ReactionType reactionType = (ReactionType) row[1];
            long count = (long) row[2];
            countsByPostMap.computeIfAbsent(postId, k -> new HashMap<>())
                    .put(reactionType, count);
        }

        Map<Long, PostReactionSummary> resultMap = new HashMap<>();
        for (Long postId: postIds) {
            Map<ReactionType, Long> typeCounts = countsByPostMap.getOrDefault(postId, Collections.emptyMap());

            long calm = typeCounts.getOrDefault(ReactionType.CALM, 0L);
            long funny = typeCounts.getOrDefault(ReactionType.FUNNY, 0L);
            long inspiring = typeCounts.getOrDefault(ReactionType.INSPIRING, 0L);
            long useful = typeCounts.getOrDefault(ReactionType.USEFUL, 0L);

            long total = calm + funny + inspiring + useful;

            resultMap.put(postId, new PostReactionSummary(
                    postId,
                    total,
                    calm,
                    useful,
                    funny,
                    inspiring
            ));
        }

        return resultMap;
    }

    @Override
    public Map<Long, ReactionType> findReactionsByUserIdAndPostIds(Long userId, Collection<Long> postIds) {
        if (postIds == null || postIds.isEmpty()) {
            return Collections.emptyMap();
        }
        List<Object[]> rows = jpa.findReactionsByUserIdAndPostIds(userId, postIds);

        Map<Long, ReactionType> userReactions = new HashMap<>();

        for (Object[] row: rows) {
            Long postId = (Long) row[0];
            ReactionType type = (ReactionType) row[1];
            userReactions.put(postId, type);
        }

        return userReactions;
    }


    private Reaction toDomain(ReactionJpaEntity entity) {
        return Reaction.reconstitute(
                entity.getId(),
                entity.getUserId(),
                entity.getPostId(),
                entity.getReactionType(),
                entity.getCreatedAt()
        );
    }

    private ReactionJpaEntity toJpaEntity(Reaction reaction) {
        return new ReactionJpaEntity(
                reaction.getId(),
                reaction.getUserId(),
                reaction.getPostId(),
                reaction.getReactionType(),
                reaction.getCreatedAt()
        );
    }
}
