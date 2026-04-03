package com.linkhub.linkhub.modes.application;

import com.linkhub.linkhub.common.error.UserModeNotFoundException;
import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.ModeRepository;
import com.linkhub.linkhub.modes.domain.UserMode;
import com.linkhub.linkhub.modes.domain.UserModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserModeUseCase {

    private final UserModeRepository userModeRepository;

    public Mode getMode(String userId) {
        UserMode userMode = userModeRepository.findByUserId(userId)
                .orElseThrow(() -> new UserModeNotFoundException(userId));

        return userMode.getMode();
    }
}
