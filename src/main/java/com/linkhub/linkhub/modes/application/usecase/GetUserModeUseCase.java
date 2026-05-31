package com.linkhub.linkhub.modes.application.usecase;

import com.linkhub.linkhub.modes.application.except.UserModeNotFoundException;
import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.UserMode;
import com.linkhub.linkhub.modes.domain.UserModeRepository;
import com.linkhub.linkhub.users.application.exception.UserNotFoundException;
import com.linkhub.linkhub.users.application.port.UserInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetUserModeUseCase {

    private final UserModeRepository userModeRepository;
    private final UserInformationPort userInformationPort;

    @Transactional(readOnly = true)
    public Mode getMode(Long userId) {
        if (!userInformationPort.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        UserMode userMode = userModeRepository.findByUserId(userId)
                .orElseThrow(() -> new UserModeNotFoundException(userId));

        return userMode.getMode();
    }
}
