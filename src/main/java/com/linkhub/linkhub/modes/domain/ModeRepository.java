package com.linkhub.linkhub.modes.domain;

import java.util.Optional;

public interface ModeRepository {
    Optional<Mode> findByName(String name);
    Mode save(Mode mode);
}
