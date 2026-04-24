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
        return jpa.findByName(name).map(this::toDomain);
    }

    @Override
    public Optional<Mode> findById(Long modeId) {
        return jpa.findById(modeId).map(this::toDomain);
    }

    @Override
    public Mode save(Mode mode) {
        ModeJpaEntity modeJpaEntity = toJpaEntity(mode);
        ModeJpaEntity saved = jpa.save(modeJpaEntity);
        return toDomain(saved);
    }

    private ModeJpaEntity toJpaEntity(Mode mode) {
        return new ModeJpaEntity(mode.getName());
    }

    private Mode toDomain(ModeJpaEntity entity) {
        return Mode.reconstitute(entity.getId(), entity.getName());
    }
}
