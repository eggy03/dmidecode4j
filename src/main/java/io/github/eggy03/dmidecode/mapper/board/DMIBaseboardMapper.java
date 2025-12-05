package io.github.eggy03.dmidecode.mapper.board;

import io.github.eggy03.dmidecode.entity.board.DMIBaseboard;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMIBaseboardMapper implements CommonDMIMapper<DMIBaseboard> {

    @Override
    public Class<DMIBaseboard> getType() {
        return DMIBaseboard.class;
    }
}
