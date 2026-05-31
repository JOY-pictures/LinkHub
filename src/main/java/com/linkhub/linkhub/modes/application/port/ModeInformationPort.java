package com.linkhub.linkhub.modes.application.port;

import com.linkhub.linkhub.modes.application.model.ModeSummary;

import java.util.Collection;
import java.util.Map;

public interface ModeInformationPort {
    ModeSummary findModeIdByName(String modeName);
    ModeSummary findModeById(Long modeId);
    Map<Long, String> findModeNameByIds(Collection<Long> modeIds);
}
