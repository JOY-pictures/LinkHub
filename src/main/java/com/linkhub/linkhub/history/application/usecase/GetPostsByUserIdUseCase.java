package com.linkhub.linkhub.history.application.usecase;

import com.linkhub.linkhub.history.application.model.HistoryPostSummary;
import com.linkhub.linkhub.history.domain.ViewedPost;
import com.linkhub.linkhub.history.domain.ViewedPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPostsByUserIdUseCase {

    private final ViewedPostRepository repository;

    @Transactional(readOnly = true)
    public List<HistoryPostSummary> get(Long userId, int limit) {
        return repository.findPostsByUserId(userId, limit)
                .stream().map(this::convert).toList();
    }

    private HistoryPostSummary convert(ViewedPost vp) {
        return new HistoryPostSummary(
                vp.getPostId(),
                vp.getViewedAt()
        );
    }
}