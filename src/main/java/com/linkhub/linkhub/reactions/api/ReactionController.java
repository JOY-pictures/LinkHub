package com.linkhub.linkhub.reactions.api;

import com.linkhub.linkhub.reactions.api.dto.CreateReactionRequest;
import com.linkhub.linkhub.reactions.api.dto.CreateReactionResponse;
import com.linkhub.linkhub.reactions.api.dto.ReactionResponse;
import com.linkhub.linkhub.reactions.application.dto.CreateReactionCommand;
import com.linkhub.linkhub.reactions.application.dto.CreateReactionResult;
import com.linkhub.linkhub.reactions.application.dto.PostReactionSummary;
import com.linkhub.linkhub.reactions.application.usecase.*;
import com.oracle.svm.core.annotate.Delete;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reactions")
@RequiredArgsConstructor
public class ReactionController {

    private final SetReactionUseCase createReactionUseCase;
    private final GetReactionByIdUseCase getReactionByIdUseCase;
    private final RemoveReactionUseCase removeReactionUseCase;
    private final GetReactionsByPostIdUseCase getReactionsByPostIdUseCase;
    private final CountReactionsOnPostUseCase countReactionsOnPostUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateReactionResponse create(@Valid @RequestBody CreateReactionRequest request) {
        CreateReactionResult result = createReactionUseCase.set(new CreateReactionCommand(
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
        return getReactionByIdUseCase.getById(id)
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

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@RequestParam Long userId,
                       @RequestParam Long postId) {
        removeReactionUseCase.remove(userId, postId);
    }

    @GetMapping("/post/{postId}")
    public List<ReactionResponse> getByPostId(@PathVariable Long postId) {
        return getReactionsByPostIdUseCase.getByPostId(postId)
                .stream()
                .map(reaction -> new ReactionResponse(
                        reaction.id(),
                        reaction.userId(),
                        reaction.postId(),
                        reaction.reactionType(),
                        reaction.createdAt()
                ))
                .toList();
    }

    @GetMapping("/post/{postId}/summary")
    public PostReactionSummary getSummary (@PathVariable Long postId) {
        return countReactionsOnPostUseCase.count(postId);
    }
}
