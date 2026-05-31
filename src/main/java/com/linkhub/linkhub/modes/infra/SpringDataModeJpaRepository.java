package com.linkhub.linkhub.modes.infra;

import com.linkhub.linkhub.modes.domain.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

interface SpringDataModeJpaRepository extends JpaRepository<ModeJpaEntity, Long> {
    Optional<ModeJpaEntity> findByName(String name);

    @Query("SELECT m.id, m.name FROM ModeJpaEntity m " +
    "WHERE m.id IN :modeIds")
    List<Object[]> findNameByIds(@Param("modeIds") Collection<Long> modeIds);
}
