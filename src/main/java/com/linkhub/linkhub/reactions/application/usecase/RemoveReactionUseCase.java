package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveReactionUseCase {

    private final ReactionRepository reactionRepository;

    public void remove(Long userId, Long postId) {
        reactionRepository.findByUserIdAndPostId(userId, postId)
                .ifPresent(reactionRepository::delete);
    }
}
