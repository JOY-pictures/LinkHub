package com.linkhub.linkhub.trust.infra;

import com.linkhub.linkhub.trust.domain.AuthorTrust;
import com.linkhub.linkhub.trust.domain.AuthorTrustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorTrustJpaAdapter implements AuthorTrustRepository {

    private final SpringDataAuthorTrustRepository jpa;

    @Override
    public AuthorTrust save(AuthorTrust authorTrust) {
        AuthorTrustJpaEntity entity = toEntity(authorTrust);
        AuthorTrustJpaEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<AuthorTrust> findByAuthorId(Long authorId) {
        return jpa.findByAuthorId(authorId).map(this::toDomain);
    }

    private AuthorTrust toDomain(AuthorTrustJpaEntity entity) {
        return AuthorTrust.reconstitute(
                entity.getId(),
                entity.getAuthorId(),
                entity.getTrustScore(),
                entity.getUpdatedAt()
        );
    }

    private AuthorTrustJpaEntity toEntity(AuthorTrust domain) {
        return new AuthorTrustJpaEntity(
                domain.getId(),
                domain.getAuthorId(),
                domain.getTrustScore(),
                domain.getUpdatedAt()
        );
    }
}
