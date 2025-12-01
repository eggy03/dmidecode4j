package io.github.eggy03.dmidecode.service.system;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.system.DMISystem;
import io.github.eggy03.dmidecode.mapper.system.DMISystemMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DMISystemService implements OptionalCommonDMIServiceInterface<DMISystem> {

    @Override
    @NotNull
    public Optional<DMISystem> get() {
        return new DMISystemMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.SYSTEM.getValue()), 10),
                DMISystem.class
        );
    }

    @Override
    @NotNull
    public Optional<DMISystem> get(long timeout) {
        return new DMISystemMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.SYSTEM.getValue()), timeout),
                DMISystem.class
        );
    }
}
