package com.linkhub.linkhub.user.application;

import com.linkhub.linkhub.common.error.UserNotFoundException;
import com.linkhub.linkhub.user.domain.User;
import com.linkhub.linkhub.user.domain.UserRepository;
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
