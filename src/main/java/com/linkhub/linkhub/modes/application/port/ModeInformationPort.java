package com.linkhub.linkhub.modes.application.port;

import com.linkhub.linkhub.modes.application.model.ModeSummary;

public interface ModeInformationPort {
    ModeSummary findModeIdByName(String modeName);
    ModeSummary findModeById(Long modeId);
}
