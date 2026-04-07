package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.application.dto.ReactionView;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReactionsByPostIdUseCase {

    private final ReactionRepository reactionRepository;

    public List<ReactionView> getByPostId(Long reactionId) {
        return reactionRepository.findByPostId(reactionId).stream().map(reaction -> {
            return new ReactionView(
                    reaction.getId(),
                    reaction.getUserId(),
                    reaction.getPostId(),
                    reaction.getReactionType(),
                    reaction.getCreatedAt()
            );
        }).toList();
    }
}