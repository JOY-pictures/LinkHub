package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountReactionsOnPostUseCase {

    private final ReactionRepository reactionRepository;

    public PostReactionSummary count (Long postId) {
        long calmCount = countType(postId, ReactionType.CALM);
        long usefulCount = countType(postId, ReactionType.USEFUL);
        long funnyCount = countType(postId, ReactionType.FUNNY);
        long inspiringCount = countType(postId, ReactionType.INSPIRING);
        long totalCount = calmCount + usefulCount + funnyCount + inspiringCount;
        return new PostReactionSummary(
                totalCount,
                postId,
                calmCount,
                usefulCount,
                funnyCount,
                inspiringCount
        );
    }

    private long countType(Long postId, ReactionType reactionType) {
        return reactionRepository.countByPostIdAndReactionType(postId, reactionType);
    }
}
