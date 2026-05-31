package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.domain.PostReactionSummary;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GetReactionsByPostIdsUseCase {
    private final ReactionRepository repository;

    @Transactional(readOnly = true)
    public Map<Long, PostReactionSummary> get (Collection<Long> postIds) {
        return repository.findSummariesByPostIds(postIds);
    }
}
