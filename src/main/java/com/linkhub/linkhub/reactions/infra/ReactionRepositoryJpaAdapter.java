package com.linkhub.linkhub.reactions.infra;

import com.linkhub.linkhub.reactions.domain.Reaction;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
