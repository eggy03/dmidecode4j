package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIPortConnectorInformation;
import io.github.eggy03.dmidecode.mapper.board.DMIPortConnectionInformationMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.List;

public class DMIPortConnectionInformationService implements CommonDMIServiceInterface<DMIPortConnectorInformation> {

    @Override
    public List<DMIPortConnectorInformation> get() {
        return new DMIPortConnectionInformationMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PORT_CONNECTOR.getValue()), 10),
                DMIPortConnectorInformation.class
        );
    }

    @Override
    public List<DMIPortConnectorInformation> get(long timeout) {
        return new DMIPortConnectionInformationMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PORT_CONNECTOR.getValue()), timeout),
                DMIPortConnectorInformation.class
        );
    }
}
