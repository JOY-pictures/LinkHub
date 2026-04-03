package com.linkhub.linkhub.content.api;

import com.linkhub.linkhub.content.api.dto.CreatePostRequest;
import com.linkhub.linkhub.content.api.dto.CreatePostResponse;
import com.linkhub.linkhub.content.api.dto.PostResponse;
import com.linkhub.linkhub.content.application.CreatePostCommand;
import com.linkhub.linkhub.content.application.CreatePostResult;
import com.linkhub.linkhub.content.application.CreatePostUseCase;
import com.linkhub.linkhub.content.application.GetPostByIdUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final GetPostByIdUseCase getPostByIdUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePostResponse create(@Valid @RequestBody CreatePostRequest request) {
        CreatePostResult result = createPostUseCase.create(
                new CreatePostCommand(request.authorId(), request.text())
        );
        return new CreatePostResponse(result.postId(), result.createdAt().toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        return getPostByIdUseCase.getById(id)
                .map(post -> ResponseEntity.ok(
                        new PostResponse(
                                post.id(),
                                post.authorId(),
                                post.text(),
                                post.createdAt()
                        )
                ))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
