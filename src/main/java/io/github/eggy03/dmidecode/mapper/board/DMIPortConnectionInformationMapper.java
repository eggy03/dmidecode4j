package io.github.eggy03.dmidecode.mapper.board;

import io.github.eggy03.dmidecode.entity.board.DMIPortConnectorInformation;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMIPortConnectionInformationMapper implements CommonDMIMapper<DMIPortConnectorInformation> {
    @Override
    public Class<DMIPortConnectorInformation> getType() {
        return DMIPortConnectorInformation.class;
    }
}
