package com.linkhub.linkhub.modes.application;

import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.ModeRepository;
import com.linkhub.linkhub.modes.domain.UserMode;
import com.linkhub.linkhub.modes.domain.UserModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetUserModeUseCase {

    private final ModeRepository modeRepository;
    private final UserModeRepository userModeRepository;

    public SetModeResult setMode(SetModeCommand request) {

        String modeName = request.modeName();
        String userId = request.userId();

        Mode mode = modeRepository.findByName(modeName)
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown mode: " + modeName));

        UserMode userMode = userModeRepository.findByUserId(userId)
                .orElseGet(() -> UserMode.create(userId, mode));

        userMode.changeMode(mode);

        UserMode saved = userModeRepository.save(userMode);
        return new SetModeResult(saved.getUserId(), saved.getMode().getName());
    }
}