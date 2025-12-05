package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBaseboard;
import io.github.eggy03.dmidecode.mapper.board.DMIBaseboardMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DMIBaseboardService implements OptionalCommonDMIServiceInterface<DMIBaseboard> {

    @Override
    @NotNull
    public Optional<DMIBaseboard> get() {
        return new DMIBaseboardMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BASEBOARD.getValue()), 10)
        );
    }

    @Override
    @NotNull
    public Optional<DMIBaseboard> get(long timeout) {
        return new DMIBaseboardMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BASEBOARD.getValue()), timeout)
        );
    }
}