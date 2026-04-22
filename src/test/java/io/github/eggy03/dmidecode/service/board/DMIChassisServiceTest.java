package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIChassis;
import io.github.eggy03.dmidecode.entity.board.ImmutableDMIChassis;
import io.github.eggy03.dmidecode.mapper.board.DMIChassisMapper;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class DMIChassisServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMIChassis mockDMIClass = new ImmutableDMIChassis.Builder()
            .manufacturer("Dell Inc.")
            .type("Desktop")
            .lock("Not Present")
            .version("1.0")
            .serialNumber("ABC123456")
            .assetTag("OFFICE-PC-01")
            .bootUpState("Safe")
            .powerSupplyState("Safe")
            .thermalState("Safe")
            .securityStatus("None")
            .oemInformation("N/A")
            .height("Unspecified")
            .numberOfPowerCords(1)
            .containedElements(0)
            .skuNumber("SKU-001")
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMIChassisMapper mapper;
    @InjectMocks
    private DMIChassisService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToEntity(anyString(), any())).thenReturn(Optional.of(mockDMIClass));

        Optional<DMIChassis> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).contains(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.CHASSIS, 15);
        inOrder.verify(mapper).mapToEntity(validTerminalResult.getResult(), DMIChassis.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToEntity(anyString(), any())).thenReturn(Optional.empty());

        Optional<DMIChassis> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.CHASSIS, 10);
        inOrder.verify(mapper).mapToEntity(validTerminalResult.getResult(), DMIChassis.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

}
