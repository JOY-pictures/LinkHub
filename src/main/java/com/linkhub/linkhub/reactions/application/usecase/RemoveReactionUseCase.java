package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoveReactionUseCase {

    private final ReactionRepository reactionRepository;

    @Transactional(readOnly = true)
    public void remove(Long userId, Long postId) {
        reactionRepository.findByUserIdAndPostId(userId, postId)
                .ifPresent(reactionRepository::delete);
    }
}
