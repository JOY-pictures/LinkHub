package com.linkhub.linkhub.user.application;

import com.linkhub.linkhub.common.error.UsernameAlreadyExistsException;
import com.linkhub.linkhub.content.application.CreatePostCommand;
import com.linkhub.linkhub.content.application.CreatePostResult;
import com.linkhub.linkhub.user.domain.User;
import com.linkhub.linkhub.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final Clock clock;

    public CreateUserUseCase(UserRepository userRepository, Clock clock) {
        this.userRepository = userRepository;
        this.clock = clock;
    }

    public CreateUserResult create(CreateUserCommand command) {
        if (userRepository.findByUsername(command.username()).isPresent()) {
            throw new UsernameAlreadyExistsException(command.username());
        }
        Instant now = Instant.now(clock);
        User user = User.create(
                command.username(),
                command.displayName(),
                now);
        User saved = userRepository.save(user);
        return new CreateUserResult(
                saved.getId(),
                saved.getUsername(),
                saved.getDisplayName(),
                saved.getCreatedAt());

    }
}
