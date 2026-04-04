package com.linkhub.linkhub.user.infra;

import com.linkhub.linkhub.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SpringDataUserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByUsername(String username);
    boolean existsById(Long id);
    boolean existsByUsername(String username);
}
