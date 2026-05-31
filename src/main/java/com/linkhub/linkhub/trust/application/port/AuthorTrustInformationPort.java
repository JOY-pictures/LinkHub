package com.linkhub.linkhub.trust.application.port;

import com.linkhub.linkhub.trust.application.model.AuthorTrustSummary;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AuthorTrustInformationPort {
    AuthorTrustSummary getTrustByAuthorId(Long authorId);
    Map<Long, Double> getTrustScoresByAuthorIds(Collection<Long> authorIds);
}
