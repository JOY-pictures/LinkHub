package com.linkhub.linkhub.history.application.service;

import com.linkhub.linkhub.history.application.model.HistoryPostSummary;
import com.linkhub.linkhub.history.application.port.HistoryInformationPort;
import com.linkhub.linkhub.history.application.usecase.GetPostIdsByUserIdUseCase;
import com.linkhub.linkhub.history.application.usecase.GetPostsByUserIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoryInformationService implements HistoryInformationPort {

    private final GetPostIdsByUserIdUseCase getPostIdsByUserIdUseCase;
    private final GetPostsByUserIdUseCase getPostsByUserIdUseCase;

    @Override
    public List<Long> getPostIdsByUserId(Long userId, int limit) {
        return getPostIdsByUserIdUseCase.get(userId, limit);
    }

    @Override
    public List<HistoryPostSummary> getPostsByUserId(Long userId, int limit) {
        return getPostsByUserIdUseCase.get(userId, limit);

    }

}
