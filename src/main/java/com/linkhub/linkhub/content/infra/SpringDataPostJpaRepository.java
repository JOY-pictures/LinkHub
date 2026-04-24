package com.linkhub.linkhub.content.infra;

import com.linkhub.linkhub.content.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SpringDataPostJpaRepository extends JpaRepository<PostJpaEntity, Long> {

    @Query("SELECT p FROM PostJpaEntity p WHERE p.modeId = :modeId")
    List<PostJpaEntity> findPostsByModeIdWithLimit(Long modeId, Pageable pageable);
}
