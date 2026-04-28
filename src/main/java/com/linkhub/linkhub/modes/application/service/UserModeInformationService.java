package com.linkhub.linkhub.modes.application.service;

import com.linkhub.linkhub.modes.application.except.UserModeNotFoundException;
import com.linkhub.linkhub.modes.application.model.ModeSummary;
import com.linkhub.linkhub.modes.application.port.UserModeInformationPort;
import com.linkhub.linkhub.modes.domain.UserMode;
import com.linkhub.linkhub.modes.domain.UserModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserModeInformationService implements UserModeInformationPort {

    private final UserModeRepository userModeRepository;

    @Override
    public ModeSummary findModeByUserId(Long userId) {
        UserMode userMode = userModeRepository.findByUserId(userId)
                .orElseThrow(() -> new UserModeNotFoundException(userId));

        return new ModeSummary(
                userMode.getMode().getId(),
                userMode.getMode().getName()
        );
    }
}
