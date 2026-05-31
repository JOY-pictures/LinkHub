package com.linkhub.linkhub.trust.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SpringDataAuthorTrustRepository extends JpaRepository<AuthorTrustJpaEntity, Long> {
    Optional<AuthorTrustJpaEntity> findByAuthorId(Long authorId);
    List<AuthorTrustJpaEntity> findByAuthorIdIn(Collection<Long> authorIds);
}
