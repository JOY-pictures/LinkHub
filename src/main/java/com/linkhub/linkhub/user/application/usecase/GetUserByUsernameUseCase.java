package com.linkhub.linkhub.user.application.usecase;

import com.linkhub.linkhub.user.application.exception.UserNotFoundException;
import com.linkhub.linkhub.user.application.dto.GetUserResult;
import com.linkhub.linkhub.user.domain.User;
import com.linkhub.linkhub.user.domain.UserRepository;
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