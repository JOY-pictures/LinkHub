package com.linkhub.linkhub.modes.infra;

import com.linkhub.linkhub.modes.domain.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SpringDataModeJpaRepository extends JpaRepository<ModeJpaEntity, Long> {
    Optional<ModeJpaEntity> findByName(String name);
}
