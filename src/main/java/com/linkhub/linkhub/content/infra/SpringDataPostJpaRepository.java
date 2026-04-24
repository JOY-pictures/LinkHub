package com.linkhub.linkhub.content.infra;

import com.linkhub.linkhub.content.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SpringDataPostJpaRepository extends JpaRepository<PostJpaEntity, Long> {

    @Query(value = """
            SELECT * FROM posts
            WHERE mode_id = :modeId
            ORDER BY created_at DESC
            LIMIT :limit
            """, nativeQuery = true)
    List<Post> findPostsByModeIdWithLimit(Long modeId, int limit);
}
