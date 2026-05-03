package com.linkhub.linkhub.reactions.application.service;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import com.linkhub.linkhub.reactions.application.port.ReactionSummaryPort;
import com.linkhub.linkhub.reactions.application.usecase.CountReactionsOnPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionSummaryService implements ReactionSummaryPort {

    private final CountReactionsOnPostUseCase countReactionsOnPostUseCase;

    @Override
    public PostReactionSummary count (Long postId) {
        return countReactionsOnPostUseCase.count(postId);
    }
}
