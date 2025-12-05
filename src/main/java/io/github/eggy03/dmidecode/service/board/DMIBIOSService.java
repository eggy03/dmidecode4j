package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBIOS;
import io.github.eggy03.dmidecode.mapper.board.DMIBIOSMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DMIBIOSService implements CommonDMIServiceInterface<DMIBIOS> {
    @Override
    @NotNull
    public List<DMIBIOS> get() {
        return new DMIBIOSMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BIOS.getValue()), 10)
        );
    }

    @Override
    @NotNull
    public List<DMIBIOS> get(long timeout) {
        return new DMIBIOSMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BIOS.getValue()), timeout)
        );
    }
}
