package com.linkhub.linkhub.reactions.application.service;

import com.linkhub.linkhub.reactions.application.dto.ReactionView;
import com.linkhub.linkhub.reactions.application.port.UserReactionPort;
import com.linkhub.linkhub.reactions.application.usecase.GetReactionByUserIdAndPostIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReactionService implements UserReactionPort {

    private final GetReactionByUserIdAndPostIdUseCase getReactionByUserIdAndPostIdUseCase;

    @Override
    public Optional<ReactionView> getReaction(Long userId, Long postId) {

        return getReactionByUserIdAndPostIdUseCase.getReaction(userId, postId);
    }

}
