package io.github.eggy03.dmidecode.mapper.board;

import io.github.eggy03.dmidecode.entity.board.DMISystemSlots;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMISystemSlotsMapper implements CommonDMIMapper<DMISystemSlots> {
    @Override
    public Class<DMISystemSlots> getType() {
        return DMISystemSlots.class;
    }
}
