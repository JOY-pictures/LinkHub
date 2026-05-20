package com.linkhub.linkhub.trust.application.usecase;

import com.linkhub.linkhub.trust.application.dto.TrustView;
import com.linkhub.linkhub.trust.application.exception.AuthorTrustNotFoundException;
import com.linkhub.linkhub.trust.domain.AuthorTrustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAuthorTrustUseCase {

    private final AuthorTrustRepository repository;

    public TrustView get(Long authorId) {
        return repository.findByAuthorId(authorId)
                .map(authorTrust -> new TrustView(
                        authorTrust.getAuthorId(),
                        authorTrust.getTrustScore(),
                        authorTrust.getUpdatedAt()
                ))
                .orElseThrow(() -> new AuthorTrustNotFoundException(authorId));
    }
}
