package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.application.dto.ReactionView;
import com.linkhub.linkhub.reactions.application.exception.ReactionNotFoundException;
import com.linkhub.linkhub.reactions.domain.Reaction;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetReactionByIdUseCase {

    private final ReactionRepository reactionRepository;

    public Optional<ReactionView> getById(Long reactionId) {
        return reactionRepository.findById(reactionId).map(reaction -> {
            return new ReactionView(
                    reaction.getId(),
                    reaction.getUserId(),
                    reaction.getPostId(),
                    reaction.getReactionType(),
                    reaction.getCreatedAt()
            );
        });
    }
}