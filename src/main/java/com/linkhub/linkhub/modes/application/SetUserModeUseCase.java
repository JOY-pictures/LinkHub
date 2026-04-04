package com.linkhub.linkhub.modes.application;

import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.ModeRepository;
import com.linkhub.linkhub.modes.domain.UserMode;
import com.linkhub.linkhub.modes.domain.UserModeRepository;
import com.linkhub.linkhub.user.application.exception.UserNotFoundException;
import com.linkhub.linkhub.user.application.port.UserInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetUserModeUseCase {

    private final ModeRepository modeRepository;
    private final UserModeRepository userModeRepository;
    private final UserInformationPort userInformationPort;

    public SetModeResult setMode(SetModeCommand command) {
        if (!userInformationPort.existsById(command.userId())) {
            throw new UserNotFoundException(command.userId());
        }

        String modeName = command.modeName();
        Long userId = command.userId();

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