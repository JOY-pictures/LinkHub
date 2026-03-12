package com.linkhub.linkhub.modes.infra;

import com.linkhub.linkhub.modes.domain.UserMode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserModeJpaRepository extends JpaRepository<UserMode, Long> {
    Optional<UserMode> findByUserId (String userId);
}
