package com.linkhub.linkhub.users.application.usecase;

import com.linkhub.linkhub.users.application.exception.UserNotFoundException;
import com.linkhub.linkhub.users.application.dto.GetUserResult;
import com.linkhub.linkhub.users.domain.User;
import com.linkhub.linkhub.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserRepository userRepository;

    public GetUserResult getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return new GetUserResult(
                user.getUsername(),
                user.getDisplayName(),
                user.getCreatedAt());
    }
}
