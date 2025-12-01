package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBaseboard;
import io.github.eggy03.dmidecode.mapper.board.DMIBaseboardMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIService;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.Optional;

public class DMIBaseboardService implements OptionalCommonDMIService<DMIBaseboard> {
    @Override
    public Optional<DMIBaseboard> get() {
        return new DMIBaseboardMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BASEBOARD.getValue()), 60),
                DMIBaseboard.class
        );
    }
}
