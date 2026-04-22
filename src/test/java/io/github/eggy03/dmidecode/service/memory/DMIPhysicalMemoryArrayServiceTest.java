package io.github.eggy03.dmidecode.service.memory;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.memory.DMIPhysicalMemoryArray;
import io.github.eggy03.dmidecode.entity.memory.ImmutableDMIPhysicalMemoryArray;
import io.github.eggy03.dmidecode.mapper.physicalmemory.DMIPhysicalMemoryArrayMapper;
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
class DMIPhysicalMemoryArrayServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMIPhysicalMemoryArray mockDMIClass = new ImmutableDMIPhysicalMemoryArray.Builder()
            .location("System Board Or Motherboard")
            .use("System Memory")
            .errorCorrectionType("Multi-bit ECC")
            .maximumCapacity("128 GB")
            .errorInformationHandle("0x0030")
            .numberOfDevices(4)
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMIPhysicalMemoryArrayMapper mapper;
    @InjectMocks
    private DMIPhysicalMemoryArrayService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToEntity(anyString(), any())).thenReturn(Optional.of(mockDMIClass));

        Optional<DMIPhysicalMemoryArray> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).contains(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.PHYSICAL_MEMORY_ARRAY, 15);
        inOrder.verify(mapper).mapToEntity(validTerminalResult.getResult(), DMIPhysicalMemoryArray.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToEntity(anyString(), any())).thenReturn(Optional.empty());

        Optional<DMIPhysicalMemoryArray> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.PHYSICAL_MEMORY_ARRAY, 10);
        inOrder.verify(mapper).mapToEntity(validTerminalResult.getResult(), DMIPhysicalMemoryArray.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
