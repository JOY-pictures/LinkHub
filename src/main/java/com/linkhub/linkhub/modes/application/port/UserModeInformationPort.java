package com.linkhub.linkhub.modes.application.port;

import com.linkhub.linkhub.modes.application.model.ModeSummary;

public interface UserModeInformationPort {
    ModeSummary findModeByUserId(Long userId);
}
