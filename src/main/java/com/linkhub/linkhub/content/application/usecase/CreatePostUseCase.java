package com.linkhub.linkhub.content.application.usecase;

import com.linkhub.linkhub.content.application.dto.CreatePostCommand;
import com.linkhub.linkhub.content.application.dto.CreatePostResult;
import com.linkhub.linkhub.content.domain.Post;
import com.linkhub.linkhub.content.domain.PostRepository;
import com.linkhub.linkhub.content.domain.PostType;
import com.linkhub.linkhub.content.domain.TextContent;
import com.linkhub.linkhub.users.application.exception.UserNotFoundException;
import com.linkhub.linkhub.users.application.port.UserInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CreatePostUseCase {

    private final PostRepository postRepository;
    private final UserInformationPort userInformationPort;
    private final Clock clock;

    public CreatePostResult create(CreatePostCommand command) {
        if (!userInformationPort.existsById(command.authorId())) {
            throw new UserNotFoundException(command.authorId());
        }

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
