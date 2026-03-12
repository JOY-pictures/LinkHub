package com.linkhub.linkhub.modes.infra;

import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.ModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ModeRepositoryJpaAdapter implements ModeRepository {

    private final SpringDataModeJpaRepository jpa;

    @Override
    public Optional<Mode> findByName(String name) {
        return jpa.findByName(name);
    }

    @Override
    public Mode save(Mode mode) {
        return jpa.save(mode);
    }
}
