package com.linkhub.linkhub.users.application.usecase;

import com.linkhub.linkhub.users.application.exception.UsernameAlreadyExistsException;
import com.linkhub.linkhub.users.application.dto.CreateUserCommand;
import com.linkhub.linkhub.users.application.dto.CreateUserResult;
import com.linkhub.linkhub.users.domain.User;
import com.linkhub.linkhub.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final Clock clock;

    public CreateUserResult create(CreateUserCommand command) {
        if (userRepository.existsByUsername(command.username())) {
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
