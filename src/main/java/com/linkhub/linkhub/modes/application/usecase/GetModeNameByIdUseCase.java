package com.linkhub.linkhub.modes.application.usecase;

import com.linkhub.linkhub.modes.application.except.ModeNotFoundException;
import com.linkhub.linkhub.modes.application.model.ModeSummary;
import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.ModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetModeNameByIdUseCase {

    private final ModeRepository repository;

    @Transactional(readOnly = true)
    public ModeSummary get(Long modeId) {
        Mode mode = repository.findById(modeId)
                .orElseThrow(() -> new ModeNotFoundException(modeId));

        return new ModeSummary(
                mode.getId(),
                mode.getName()
        );
    }

}
