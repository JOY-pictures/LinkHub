package com.linkhub.linkhub.users.application.service;

import com.linkhub.linkhub.users.application.model.UserSummary;
import com.linkhub.linkhub.users.application.port.UserInformationPort;
import com.linkhub.linkhub.users.domain.User;
import com.linkhub.linkhub.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInformationService implements UserInformationPort {

    private final UserRepository userRepository;

    @Override
    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<UserSummary> findById(Long userId) {
        return userRepository.findById(userId)
                .map(this::toSummary);
    }

    @Override
    public Optional<UserSummary> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::toSummary);
    }

    private UserSummary toSummary(User user) {
        return new UserSummary(
                user.getId(),
                user.getUsername(),
                user.getDisplayName(),
                user.getCreatedAt()
        );
    }
}
