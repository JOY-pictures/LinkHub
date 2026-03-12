package com.linkhub.linkhub.modes.infra;

import com.linkhub.linkhub.modes.domain.UserMode;
import com.linkhub.linkhub.modes.domain.UserModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserModeRepositoryJpaAdapter implements UserModeRepository {

    private final SpringDataUserModeJpaRepository jpa;

    @Override
    public Optional<UserMode> findByUserId(String userId) {
        return jpa.findByUserId(userId);
    }

    @Override
    public UserMode save(UserMode userMode) {
        return jpa.save(userMode);
    }
}
