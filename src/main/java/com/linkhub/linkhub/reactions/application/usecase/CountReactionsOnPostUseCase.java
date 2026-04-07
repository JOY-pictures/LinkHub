package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.application.dto.PostReactionSummary;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountReactionsOnPostUseCase {

    private final ReactionRepository reactionRepository;

    public PostReactionSummary count (Long postId) {
        return new PostReactionSummary(
                postId,
                countType(postId, ReactionType.CALM),
                countType(postId, ReactionType.USEFUL),
                countType(postId, ReactionType.FUNNY),
                countType(postId, ReactionType.INSPIRING)
        );
    }

    private long countType(Long postId, ReactionType reactionType) {
        return reactionRepository.countByPostIdAndReactionType(postId, reactionType);
    }
}
