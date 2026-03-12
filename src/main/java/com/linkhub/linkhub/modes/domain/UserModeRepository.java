package com.linkhub.linkhub.modes.domain;

import java.util.Optional;

public interface UserModeRepository {

    Optional<UserMode> findByUserId(String userId);

    UserMode save(UserMode userMode);
}
