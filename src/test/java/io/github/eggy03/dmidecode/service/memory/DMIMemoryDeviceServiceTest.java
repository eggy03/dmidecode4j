package io.github.eggy03.dmidecode.service.memory;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.memory.DMIMemoryDevice;
import io.github.eggy03.dmidecode.entity.memory.ImmutableDMIMemoryDevice;
import io.github.eggy03.dmidecode.mapper.physicalmemory.DMIMemoryDeviceMapper;
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
class DMIMemoryDeviceServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMIMemoryDevice mockDMIClass = new ImmutableDMIMemoryDevice.Builder()
            .arrayHandle("0x002C")
            .errorInformationHandle("0x002D")
            .totalWidth("64 bits")
            .dataWidth("64 bits")
            .size("16 GB")
            .formFactor("DIMM")
            .set("None")
            .locator("DIMM_A1")
            .bankLocator("BANK 0")
            .type("DDR4")
            .typeDetail("Synchronous")
            .speed("3200 MT/s")
            .manufacturer("Samsung")
            .serialNumber("12345678")
            .assetTag("Not Specified")
            .partNumber("M378A2K43CB1-CTD")
            .rank(2)
            .configuredMemorySpeed("2933 MT/s")
            .minimumVoltage("1.2 V")
            .maximumVoltage("1.2 V")
            .configuredVoltage("1.2 V")
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMIMemoryDeviceMapper mapper;
    @InjectMocks
    private DMIMemoryDeviceService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.singletonList(mockDMIClass));

        List<DMIMemoryDevice> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).containsExactlyInAnyOrder(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.MEMORY_DEVICE, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIMemoryDevice.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.emptyList());

        List<DMIMemoryDevice> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.MEMORY_DEVICE, 10);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIMemoryDevice.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
