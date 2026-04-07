package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.application.dto.ReactionView;
import com.linkhub.linkhub.reactions.domain.Reaction;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//@RequiredArgsConstructor
//public class GetReactionByUserIdAndPostIdUseCase {
//
//    private final ReactionRepository reactionRepository;
//
//    public Optional<ReactionView> getReaction(Long userId, Long postId) {
//        return reactionRepository.findByUserIdAndPostId(userId, postId).
//                map(reaction -> {
//                    return new ReactionView(
//                            reaction.getId(),
//                            reaction.getUserId(),
//                            reaction.getPostId(),
//                            reaction.getReactionType(),
//                            reaction.getCreatedAt()
//                    );
//                });
//    }
//}
