package com.linkhub.linkhub.content.infra;

import com.linkhub.linkhub.content.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataPostJpaRepository extends JpaRepository<PostJpaEntity, Long> {
}
