package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMISystemSlots;
import io.github.eggy03.dmidecode.mapper.board.DMISystemSlotsMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.List;

public class DMISystemSlotsService implements CommonDMIServiceInterface<DMISystemSlots> {
    @Override
    public List<DMISystemSlots> get() {
        return new DMISystemSlotsMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.SYSTEM_SLOTS.getValue()), 10)
        );
    }

    @Override
    public List<DMISystemSlots> get(long timeout) {
        return new DMISystemSlotsMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.SYSTEM_SLOTS.getValue()), timeout)
        );
    }
}
