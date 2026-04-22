package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBaseboard;
import io.github.eggy03.dmidecode.entity.board.ImmutableDMIBaseboard;
import io.github.eggy03.dmidecode.mapper.board.DMIBaseboardMapper;
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

import java.util.Arrays;
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
class DMIBaseboardServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMIBaseboard mockDMIClass = new ImmutableDMIBaseboard.Builder()
            .manufacturer("ASUSTeK COMPUTER INC.")
            .productName("PRIME B550M-A")
            .version("Rev X.0x")
            .serialNumber("ABC123456")
            .assetTag("Default string")
            .features(Arrays.asList(
                    "Board is a hosting board",
                    "Board is replaceable"
            ))
            .locationInChassis("Default string")
            .chassisHandle("0x0003")
            .type("Motherboard")
            .containedObjectHandles(0)
            .build();
    @Mock
    private TerminalService terminalService;
    @Mock
    private DMIBaseboardMapper mapper;
    @InjectMocks
    private DMIBaseboardService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToEntity(anyString(), any())).thenReturn(Optional.of(mockDMIClass));

        Optional<DMIBaseboard> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).contains(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.BASEBOARD, 15);
        inOrder.verify(mapper).mapToEntity(validTerminalResult.getResult(), DMIBaseboard.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToEntity(anyString(), any())).thenReturn(Optional.empty());

        Optional<DMIBaseboard> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.BASEBOARD, 10);
        inOrder.verify(mapper).mapToEntity(validTerminalResult.getResult(), DMIBaseboard.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
