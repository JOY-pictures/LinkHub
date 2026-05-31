package com.linkhub.linkhub.history.application.usecase;

import com.linkhub.linkhub.history.domain.ViewedPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPostIdsByUserIdUseCase {

    private final ViewedPostRepository repository;

    @Transactional(readOnly = true)
    public List<Long> get(Long userId, int limit) {
        int lim = Math.min(limit, 5000);
        return repository.findPostIdsByUserId(userId, lim);
    }
}
