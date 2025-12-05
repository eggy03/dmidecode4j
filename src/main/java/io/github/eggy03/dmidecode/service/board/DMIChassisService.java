package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIChassis;
import io.github.eggy03.dmidecode.mapper.board.DMIChassisMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DMIChassisService implements OptionalCommonDMIServiceInterface<DMIChassis> {

    @Override
    @NotNull
    public Optional<DMIChassis> get() {
        return new DMIChassisMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.CHASSIS.getValue()), 10)
        );
    }

    @Override
    @NotNull
    public Optional<DMIChassis> get(long timeout) {
        return new DMIChassisMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.CHASSIS.getValue()), timeout)
        );
    }
}
