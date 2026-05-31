package com.linkhub.linkhub.trust.application.usecase;

import com.linkhub.linkhub.trust.application.dto.TrustView;
import com.linkhub.linkhub.trust.application.exception.AuthorTrustNotFoundException;
import com.linkhub.linkhub.trust.domain.AuthorTrust;
import com.linkhub.linkhub.trust.domain.AuthorTrustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAuthorsTrustUseCase {

    private final AuthorTrustRepository repository;

    @Transactional(readOnly = true)
    public List<TrustView> get(Collection<Long> authorIds) {
        return repository.findAllByAuthorIds(authorIds).stream().map(t -> {
            return new TrustView(
                    t.getAuthorId(),
                    t.getTrustScore(),
                    t.getUpdatedAt()
            );
        }).toList();
    }
}
