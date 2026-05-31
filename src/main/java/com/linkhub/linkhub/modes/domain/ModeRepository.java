package com.linkhub.linkhub.modes.domain;


import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface ModeRepository {
    Optional<Mode> findById(Long modeId);
    Optional<Mode> findByName(String name);
    Mode save(Mode mode);
    Map<Long, String> findModeNameByIds(Collection<Long> modeIds);
}
