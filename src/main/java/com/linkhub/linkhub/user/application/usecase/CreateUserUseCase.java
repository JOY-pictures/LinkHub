package com.linkhub.linkhub.user.application.usecase;

import com.linkhub.linkhub.user.application.exception.UsernameAlreadyExistsException;
import com.linkhub.linkhub.user.application.dto.CreateUserCommand;
import com.linkhub.linkhub.user.application.dto.CreateUserResult;
import com.linkhub.linkhub.user.domain.User;
import com.linkhub.linkhub.user.domain.UserRepository;
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
