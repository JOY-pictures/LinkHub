package com.linkhub.linkhub.users.application.port;

import com.linkhub.linkhub.users.application.model.UserSummary;

import java.util.Optional;

public interface UserInformationPort {
    boolean existsById(Long userId);
    boolean existsByUsername(String username);
    Optional<UserSummary> findById(Long userId);
    Optional<UserSummary> findByUsername(String username);
}
