package com.linkhub.linkhub.modes.application.service;

import com.linkhub.linkhub.modes.application.except.ModeNotFoundException;
import com.linkhub.linkhub.modes.application.model.ModeSummary;
import com.linkhub.linkhub.modes.application.port.ModeInformationPort;
import com.linkhub.linkhub.modes.application.usecase.GetModeIdByNameUseCase;
import com.linkhub.linkhub.modes.application.usecase.GetModeNameByIdUseCase;
import com.linkhub.linkhub.modes.application.usecase.GetModeNamesByIdsUseCase;
import com.linkhub.linkhub.modes.domain.Mode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ModeInformationService implements ModeInformationPort {

    private final GetModeIdByNameUseCase getModeIdByNameUseCase;
    private final GetModeNameByIdUseCase getModeNameByIdUseCase;
    private final GetModeNamesByIdsUseCase getModeNamesByIdsUseCase;

    @Override
    public ModeSummary findModeIdByName(String modeName) {
        return getModeIdByNameUseCase.get(modeName);
    }

    @Override
    public ModeSummary findModeById(Long modeId) {
        return getModeNameByIdUseCase.get(modeId);
    }

    @Override
    public Map<Long, String> findModeNameByIds(Collection<Long> modeIds) {
        return getModeNamesByIdsUseCase.get(modeIds);
    }

}
