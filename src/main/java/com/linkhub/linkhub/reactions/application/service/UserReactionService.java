package com.linkhub.linkhub.reactions.application.service;

import com.linkhub.linkhub.reactions.application.usecase.GetReactionsByUserIdPostIdsUseCase;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import com.linkhub.linkhub.reactions.domain.ReactionView;
import com.linkhub.linkhub.reactions.application.port.UserReactionPort;
import com.linkhub.linkhub.reactions.application.usecase.GetReactionByUserIdAndPostIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReactionService implements UserReactionPort {

    private final GetReactionByUserIdAndPostIdUseCase getReactionByUserIdAndPostIdUseCase;
    private final GetReactionsByUserIdPostIdsUseCase getReactionsByUserIdPostIdsUseCase;

    @Override
    public Optional<ReactionView> getReactionByUserIdAndPostId(Long userId, Long postId) {
        return getReactionByUserIdAndPostIdUseCase.get(userId, postId);
    }

    @Override
    public Map<Long, ReactionType> getReactionsByUserIdAndPostIds(Long userId, Collection<Long> postIds) {
        return getReactionsByUserIdPostIdsUseCase.get(userId, postIds);
    }
}