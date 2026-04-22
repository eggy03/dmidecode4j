package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMISystemSlots;
import io.github.eggy03.dmidecode.entity.board.DMISystemSlots;
import io.github.eggy03.dmidecode.entity.board.ImmutableDMISystemSlots;
import io.github.eggy03.dmidecode.mapper.board.DMISystemSlotsMapper;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
class DMISystemSlotsServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMISystemSlots mockDMIClass = new ImmutableDMISystemSlots.Builder()
            .designation("PCIEX16")
            .type("PCI Express")
            .currentUsage("In Use")
            .length("Long")
            .id(1)
            .characteristics(Arrays.asList(
                    "3.3 V is provided",
                    "PME signal is supported"
            ))
            .busAddress("0000:01:00.0")
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMISystemSlotsMapper mapper;
    @InjectMocks
    private DMISystemSlotsService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.singletonList(mockDMIClass));

        List<DMISystemSlots> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).containsExactlyInAnyOrder(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.SYSTEM_SLOTS, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMISystemSlots.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.emptyList());

        List<DMISystemSlots> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.SYSTEM_SLOTS, 10);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMISystemSlots.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
