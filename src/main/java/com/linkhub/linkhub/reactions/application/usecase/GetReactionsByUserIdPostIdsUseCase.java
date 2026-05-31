package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GetReactionsByUserIdPostIdsUseCase {

    private final ReactionRepository repository;

    public Map<Long, ReactionType> get(Long userId, Collection<Long> postIds) {
        return repository.findReactionsByUserIdAndPostIds(userId, postIds);
    }
}
