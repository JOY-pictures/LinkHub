package com.linkhub.linkhub.modes.application;

import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.ModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetUserModeUseCase {
    private final ModeRepository modeRepository;

    public Mode setMode(String modeName) {
        return modeRepository.findByName(modeName)
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown mode: " + modeName));
    }
}
