package io.github.eggy03.dmidecode.service.processor;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.processor.DMIProcessor;
import io.github.eggy03.dmidecode.entity.processor.ImmutableDMIProcessor;
import io.github.eggy03.dmidecode.mapper.processor.DMIProcessorMapper;
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
class DMIProcessorServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMIProcessor mockDMIClass = new ImmutableDMIProcessor.Builder()
            .socketDesignation("CPU0")
            .type("Central Processor")
            .family("Core i7")
            .manufacturer("Intel")
            .id("BFEBFBFF000806EA")
            .signature("Type 0, Family 6, Model 154, Stepping 3")
            .flags(Arrays.asList(
                    "fpu",
                    "vme",
                    "sse",
                    "sse2"
            ))
            .version("Intel(R) Core(TM) i7-12700H")
            .voltage("1.2 V")
            .externalClock("100 MHz")
            .maxSpeed("4700 MHz")
            .currentSpeed("2700 MHz")
            .status("Populated, Enabled")
            .upgrade("Socket BGA1744")
            .l1CacheHandle("0x0007")
            .l2CacheHandle("0x0008")
            .l3CacheHandle("0x0009")
            .serialNumber("To Be Filled By O.E.M.")
            .assetTag("Not Specified")
            .partNumber("Not Specified")
            .coreCount(14)
            .coreEnabled(14)
            .threadCount(20)
            .characteristics(Arrays.asList(
                    "64-bit capable",
                    "Multi-Core",
                    "Hardware Thread"
            ))
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMIProcessorMapper mapper;
    @InjectMocks
    private DMIProcessorService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.singletonList(mockDMIClass));

        List<DMIProcessor> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).containsExactlyInAnyOrder(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.PROCESSOR, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIProcessor.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.emptyList());

        List<DMIProcessor> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.PROCESSOR, 10);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIProcessor.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
