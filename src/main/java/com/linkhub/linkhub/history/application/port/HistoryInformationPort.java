package com.linkhub.linkhub.history.application.port;

import com.linkhub.linkhub.history.application.model.HistoryPostSummary;

import java.util.List;

public interface HistoryInformationPort {
    List<Long> getPostIdsByUserId(Long userId, int limit);
    List<HistoryPostSummary> getPostsByUserId(Long userId, int limit);
}
