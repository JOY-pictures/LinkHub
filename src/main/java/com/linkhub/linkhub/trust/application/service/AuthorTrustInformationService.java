package com.linkhub.linkhub.trust.application.service;

import com.linkhub.linkhub.trust.application.dto.TrustView;
import com.linkhub.linkhub.trust.application.model.AuthorTrustSummary;
import com.linkhub.linkhub.trust.application.port.AuthorTrustInformationPort;
import com.linkhub.linkhub.trust.application.usecase.GetAuthorTrustUseCase;
import com.linkhub.linkhub.trust.application.usecase.GetAuthorsTrustUseCase;
import com.linkhub.linkhub.trust.domain.AuthorTrust;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorTrustInformationService implements AuthorTrustInformationPort {

    private final GetAuthorTrustUseCase getAuthorTrustUseCase;
    private final GetAuthorsTrustUseCase getAuthorsTrustUseCase;

    @Override
    public AuthorTrustSummary getTrustByAuthorId(Long authorId) {
        TrustView trustView = getAuthorTrustUseCase.get(authorId);
        return new AuthorTrustSummary(
                trustView.authorId(),
                trustView.trustScore(),
                trustView.updatedAt()
        );
    }

    @Override
    public Map<Long, Double> getTrustScoresByAuthorIds(Collection<Long> authorIds) {
        if (authorIds == null || authorIds.isEmpty()) {
            return Collections.emptyMap();
        }

        List<TrustView> trusts = getAuthorsTrustUseCase.get(authorIds);

        Map<Long, Double> existTrustMap = trusts.stream()
                .collect(Collectors.toMap(TrustView::authorId, TrustView::trustScore));

        Map<Long, Double> resultMap = new HashMap<>();

        for (Long authorId : authorIds) {
            resultMap.put(authorId, existTrustMap.getOrDefault(authorId, 1.0));
        }

        return resultMap;
    }
}