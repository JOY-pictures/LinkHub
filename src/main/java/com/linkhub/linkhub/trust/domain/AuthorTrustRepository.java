package com.linkhub.linkhub.trust.domain;

import java.util.Optional;

public interface AuthorTrustRepository {
    AuthorTrust save(AuthorTrust authorTrust);
    Optional<AuthorTrust> findByAuthorId(Long authorId);
}
