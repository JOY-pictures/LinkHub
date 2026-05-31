package com.linkhub.linkhub.reactions.application.service;

import com.linkhub.linkhub.reactions.application.usecase.GetReactionsByPostIdsUseCase;
import com.linkhub.linkhub.reactions.domain.PostReactionSummary;
import com.linkhub.linkhub.reactions.application.port.ReactionSummaryPort;
import com.linkhub.linkhub.reactions.application.usecase.CountReactionsOnPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReactionSummaryService implements ReactionSummaryPort {

    private final CountReactionsOnPostUseCase countReactionsOnPostUseCase;
    private final GetReactionsByPostIdsUseCase getReactionsByPostIdsUseCase;

    @Override
    public PostReactionSummary getReactionSummaryByPostId (Long postId) {
        return countReactionsOnPostUseCase.count(postId);
    }

    @Override
    public Map<Long, PostReactionSummary> getReactionSummariesByPostIds (Collection<Long> postIds) {
        return getReactionsByPostIdsUseCase.get(postIds);
    }

}
