package com.linkhub.linkhub.modes.domain;

import org.hibernate.mapping.Collection;

import java.util.Map;
import java.util.Optional;

public interface UserModeRepository {

    Optional<UserMode> findByUserId(Long userId);

    UserMode save(UserMode userMode);
}
