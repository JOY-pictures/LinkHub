package com.linkhub.linkhub.modes.application.usecase;

import com.linkhub.linkhub.modes.domain.ModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GetModeNamesByIdsUseCase {

    private final ModeRepository repository;

    @Transactional(readOnly = true)
    public Map<Long, String> get(Collection<Long> modeIds) {
        return repository.findModeNameByIds(modeIds);
    }
}
