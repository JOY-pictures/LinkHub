package com.linkhub.linkhub.reactions.application.service;

import com.linkhub.linkhub.reactions.application.dto.ReactionView;
import com.linkhub.linkhub.reactions.application.usecase.GetReactionByUserIdAndPostIdUseCase;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import com.linkhub.linkhub.reactions.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReactionService {

    private final GetReactionByUserIdAndPostIdUseCase getReactionByUserIdAndPostIdUseCase;

    @Override
    public Optional<ReactionView> getReaction(Long userId, Long postId) {

        return getReactionByUserIdAndPostIdUseCase.getReaction(userId, postId);
    }

}
