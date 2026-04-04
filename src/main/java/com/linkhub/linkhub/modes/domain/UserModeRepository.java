package com.linkhub.linkhub.modes.domain;

import java.util.Optional;

public interface UserModeRepository {

    Optional<UserMode> findByUserId(Long userId);

    UserMode save(UserMode userMode);
}
