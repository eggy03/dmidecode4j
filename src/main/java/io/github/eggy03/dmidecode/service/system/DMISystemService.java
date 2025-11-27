package io.github.eggy03.dmidecode.service.system;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.system.DMISystem;
import io.github.eggy03.dmidecode.mapper.system.DMISystemMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIService;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.Optional;

public class DMISystemService implements OptionalCommonDMIService<DMISystem> {

    @Override
    public Optional<DMISystem> get() {
        return new DMISystemMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.SYSTEM.getValue()), 60),
                DMISystem.class
        );
    }
}
