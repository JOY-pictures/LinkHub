package com.linkhub.linkhub.trust.application.port;

import com.linkhub.linkhub.trust.application.model.AuthorTrustSummary;

public interface AuthorTrustInformationPort {
    AuthorTrustSummary getTrustByAuthorId(Long authorId);
}
