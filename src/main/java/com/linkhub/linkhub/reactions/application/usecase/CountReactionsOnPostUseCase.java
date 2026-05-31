package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.domain.PostReactionSummary;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountReactionsOnPostUseCase {

    private final ReactionRepository repository;

    @Transactional(readOnly = true)
    public PostReactionSummary count (Long postId) {
        return repository.findSummaryByPostId(postId);
    }
}
