package com.linkhub.linkhub.users.application.usecase;

import com.linkhub.linkhub.users.application.exception.UserNotFoundException;
import com.linkhub.linkhub.users.application.dto.GetUserResult;
import com.linkhub.linkhub.users.domain.User;
import com.linkhub.linkhub.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByUsernameUseCase {

    private final UserRepository repository;

    public GetUserResult getByUsername(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return new GetUserResult(
                user.getUsername(),
                user.getDisplayName(),
                user.getCreatedAt());
    }
}