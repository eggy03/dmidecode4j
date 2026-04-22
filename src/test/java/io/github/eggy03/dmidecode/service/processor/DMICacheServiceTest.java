package io.github.eggy03.dmidecode.service.processor;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.processor.DMICache;
import io.github.eggy03.dmidecode.entity.processor.ImmutableDMICache;
import io.github.eggy03.dmidecode.mapper.processor.DMICacheMapper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class DMICacheServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMICache mockDMIClass = new ImmutableDMICache.Builder()
            .socketDesignation("L3-Cache")
            .configuration("Enabled, Not Socketed, Level 3")
            .operationalMode("Write Back")
            .location("Internal")
            .installedSize("32 MB")
            .maximumSize("64 MB")
            .supportedSramTypes(Arrays.asList(
                    "Pipeline Burst",
                    "Synchronous"
            ))
            .installedSramType("Pipeline Burst")
            .speed("Unknown")
            .errorCorrectionType("Multi-bit ECC")
            .systemType("Unified")
            .associativity("16-way Set-Associative")
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMICacheMapper mapper;
    @InjectMocks
    private DMICacheService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.singletonList(mockDMIClass));

        List<DMICache> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).containsExactlyInAnyOrder(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.CACHE, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMICache.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.emptyList());

        List<DMICache> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.CACHE, 10);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMICache.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
