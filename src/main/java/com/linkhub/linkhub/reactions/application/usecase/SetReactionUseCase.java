package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.content.application.except.PostNotFoundException;
import com.linkhub.linkhub.content.application.port.PostInformationPort;
import com.linkhub.linkhub.reactions.application.dto.CreateReactionCommand;
import com.linkhub.linkhub.reactions.application.dto.CreateReactionResult;
import com.linkhub.linkhub.reactions.domain.Reaction;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import com.linkhub.linkhub.users.application.exception.UserNotFoundException;
import com.linkhub.linkhub.users.application.port.UserInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SetReactionUseCase {

    private final ReactionRepository reactionRepository;
    private final PostInformationPort postInformationPort;
    private final UserInformationPort userInformationPort;
    private final Clock clock;

    public CreateReactionResult set(CreateReactionCommand command) {
        if (!postInformationPort.existsById(command.postId())) {
            throw new PostNotFoundException(command.postId());
        }
        if (!userInformationPort.existsById(command.userId())) {
            throw new UserNotFoundException(command.userId());
        }
        Instant now = Instant.now(clock);
        Optional<Reaction> existing = reactionRepository.findByUserIdAndPostId(command.userId(), command.postId());
        if (existing.isPresent()) {
            Reaction reaction = existing.get();
            reaction.changeType(command.reactionType());
            Reaction saved = reactionRepository.save(reaction);
            return new CreateReactionResult(
                    saved.getId(),
                    saved.getUserId(),
                    saved.getPostId(),
                    saved.getReactionType(),
                    saved.getCreatedAt()
            );
        }


        Reaction reaction = Reaction.create(
                command.userId(),
                command.postId(),
                command.reactionType(),
                now);
        Reaction saved = reactionRepository.save(reaction);
        return new CreateReactionResult(
                saved.getId(),
                saved.getUserId(),
                saved.getPostId(),
                saved.getReactionType(),
                saved.getCreatedAt()
        );
    }
}
