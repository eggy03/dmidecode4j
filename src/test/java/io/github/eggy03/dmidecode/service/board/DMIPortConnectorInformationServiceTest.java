package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIPortConnectorInformation;
import io.github.eggy03.dmidecode.entity.board.ImmutableDMIPortConnectorInformation;
import io.github.eggy03.dmidecode.mapper.board.DMIPortConnectorInformationMapper;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class DMIPortConnectorInformationServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMIPortConnectorInformation mockDMIClass = new ImmutableDMIPortConnectorInformation.Builder()
            .externalReferenceDesignator("USB1")
            .internalReferenceDesignator("JUSB1")
            .externalConnectorType("USB")
            .internalConnectorType("USB")
            .portType("USB")
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMIPortConnectorInformationMapper mapper;
    @InjectMocks
    private DMIPortConnectorInformationService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.singletonList(mockDMIClass));

        List<DMIPortConnectorInformation> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).containsExactlyInAnyOrder(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.PORT_CONNECTOR, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIPortConnectorInformation.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.emptyList());

        List<DMIPortConnectorInformation> result = service.get(15);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.PORT_CONNECTOR, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIPortConnectorInformation.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}

