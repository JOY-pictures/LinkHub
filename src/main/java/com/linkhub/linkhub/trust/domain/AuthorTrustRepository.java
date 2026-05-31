package com.linkhub.linkhub.trust.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AuthorTrustRepository {
    AuthorTrust save(AuthorTrust authorTrust);
    Optional<AuthorTrust> findByAuthorId(Long authorId);
    List<AuthorTrust> findAllByAuthorIds(Collection<Long> authorIds);
}
