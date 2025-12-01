package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIChassis;
import io.github.eggy03.dmidecode.mapper.board.DMIChassisMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIService;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.Optional;

public class DMIChassisService implements OptionalCommonDMIService<DMIChassis> {

    @Override
    public Optional<DMIChassis> get() {
        return new DMIChassisMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.CHASSIS.getValue()), 60),
                DMIChassis.class
        );
    }
}
