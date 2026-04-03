package com.linkhub.linkhub.content.application;

import com.linkhub.linkhub.content.domain.Post;
import com.linkhub.linkhub.content.domain.PostRepository;
import com.linkhub.linkhub.content.domain.PostType;
import com.linkhub.linkhub.content.domain.TextContent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
public class CreatePostUseCase {
    private final PostRepository postRepository;
    private final Clock clock;

    public CreatePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.clock = Clock.systemUTC();
    }

    public CreatePostResult create(CreatePostCommand command) {
        Instant now = Instant.now(clock);
        TextContent content = TextContent.create(command.text());
        Post post = Post.create(
                command.authorId(),
                now,
                PostType.TEXT,
                content
        );
        Post saved = postRepository.save(post);
        return new CreatePostResult(saved.getId(), saved.getCreatedAt());
    }
}
