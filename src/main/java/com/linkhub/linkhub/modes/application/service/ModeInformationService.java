package com.linkhub.linkhub.modes.application.service;

import com.linkhub.linkhub.modes.application.except.ModeNotFoundException;
import com.linkhub.linkhub.modes.application.port.ModeInformationPort;
import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.ModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModeInformationService implements ModeInformationPort {

    private final ModeRepository modeRepository;

    @Override
    public Long findModeIdByName(String modeName) {
        Mode mode = modeRepository.findByName(modeName)
                .orElseThrow(() -> new ModeNotFoundException(modeName));

        return mode.getId();
    }
}
