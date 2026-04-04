package com.linkhub.linkhub.modes.infra;

import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.UserMode;
import com.linkhub.linkhub.modes.domain.UserModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserModeRepositoryJpaAdapter implements UserModeRepository {

    private final SpringDataUserModeJpaRepository jpa;
    private final SpringDataModeJpaRepository modeJpaRepository;

    @Override
    public Optional<UserMode> findByUserId(Long userId) {
        return jpa.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public UserMode save(UserMode userMode) {
        UserModeJpaEntity entity = toJpaEntity(userMode);
        UserModeJpaEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    private UserMode toDomain(UserModeJpaEntity entity) {
        Mode mode = Mode.reconstitute(entity.getMode().getId(), entity.getMode().getName());
        return UserMode.reconstitute(entity.getId(), entity.getUserId(), mode);
    }

    private UserModeJpaEntity toJpaEntity(UserMode userMode) {
        ModeJpaEntity modeEntity = modeJpaRepository.findByName(userMode.getMode().getName()).
                orElseThrow(() ->
                        new IllegalArgumentException("Unknown mode: " + userMode.getMode().getName()));

        if (userMode.getId() != null) {
            return new UserModeJpaEntity(userMode.getId(), userMode.getUserId(), modeEntity);
        }
        return new UserModeJpaEntity(userMode.getUserId(), modeEntity);
    }
}
