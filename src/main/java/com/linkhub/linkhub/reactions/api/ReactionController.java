package com.linkhub.linkhub.reactions.api;

import com.linkhub.linkhub.reactions.api.dto.CreateReactionRequest;
import com.linkhub.linkhub.reactions.api.dto.CreateReactionResponse;
import com.linkhub.linkhub.reactions.api.dto.ReactionResponse;
import com.linkhub.linkhub.reactions.application.dto.CreateReactionCommand;
import com.linkhub.linkhub.reactions.application.dto.CreateReactionResult;
import com.linkhub.linkhub.reactions.application.usecase.SetReactionUseCase;
import com.linkhub.linkhub.reactions.application.usecase.GetReactionByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reactions")
@RequiredArgsConstructor
public class ReactionController {

    private final SetReactionUseCase createReactionUseCase;
    private final GetReactionByIdUseCase getReactionByIdUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateReactionResponse create(@Valid @RequestBody CreateReactionRequest request) {
        CreateReactionResult result = createReactionUseCase.create(new CreateReactionCommand(
                request.userId(), request.postId(), request.reactionType()
        ));
        return new CreateReactionResponse(result.id(),
                result.userId(),
                result.postId(),
                result.reactionType(),
                result.createdAt());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReactionResponse> getById(@PathVariable Long id) {
        return getReactionByIdUseCase.getReaction(id)
                .map(reaction -> ResponseEntity.ok(
                        new ReactionResponse(
                                reaction.id(),
                                reaction.userId(),
                                reaction.postId(),
                                reaction.reactionType(),
                                reaction.createdAt()
                        )
                )).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
