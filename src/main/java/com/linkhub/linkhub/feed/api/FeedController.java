package com.linkhub.linkhub.feed.api;

import com.linkhub.linkhub.feed.api.dto.FeedPostRequest;
import com.linkhub.linkhub.feed.api.dto.FeedPostResponse;
import com.linkhub.linkhub.feed.application.dto.GetFeedCommand;
import com.linkhub.linkhub.feed.application.usecase.GetFeedUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final GetFeedUseCase getFeedUseCase;

    @GetMapping
    public List<FeedPostResponse> getFeed(@Valid @RequestBody FeedPostRequest request) {
        return getFeedUseCase.getFeed(new GetFeedCommand(request.userId(), request.limit()))
                .stream()
                .map(post -> new FeedPostResponse(
                        post.postId(),
                        post.authorId(),
                        post.text(),
                        post.modeId(),
                        post.modeName(),
                        post.createdAt(),
                        post.reason()
                ))
                .toList();
    }
}
