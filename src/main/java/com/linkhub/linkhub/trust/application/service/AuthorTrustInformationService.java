package com.linkhub.linkhub.trust.application.service;

import com.linkhub.linkhub.trust.application.dto.TrustView;
import com.linkhub.linkhub.trust.application.model.AuthorTrustSummary;
import com.linkhub.linkhub.trust.application.port.AuthorTrustInformationPort;
import com.linkhub.linkhub.trust.application.usecase.GetAuthorTrustUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorTrustInformationService implements AuthorTrustInformationPort {

    private final GetAuthorTrustUseCase getAuthorTrustUseCase;

    @Override
    public AuthorTrustSummary getTrustByAuthorId(Long authorId) {
        TrustView trustView = getAuthorTrustUseCase.get(authorId);
        return new AuthorTrustSummary(
                trustView.authorId(),
                trustView.trustScore(),
                trustView.updatedAt()
        );
    }
}
