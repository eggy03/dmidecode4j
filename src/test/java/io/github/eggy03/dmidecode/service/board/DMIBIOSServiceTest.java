package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBIOS;
import io.github.eggy03.dmidecode.entity.board.ImmutableDMIBIOS;
import io.github.eggy03.dmidecode.mapper.board.DMIBIOSMapper;
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
class DMIBIOSServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMIBIOS mockDMIClass = new ImmutableDMIBIOS.Builder()
            .vendor("American Megatrends Inc.")
            .version("F10")
            .releaseDate("07/15/2023")
            .address("0xF0000")
            .runtimeSize("64 kB")
            .romSize("16 MB")
            .characteristics(Arrays.asList(
                    "PCI is supported",
                    "BIOS is upgradeable"
            ))
            .biosRevision("5.17")
            .firmwareRevision("1.12")
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMIBIOSMapper mapper;
    @InjectMocks
    private DMIBIOSService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.singletonList(mockDMIClass));

        List<DMIBIOS> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).containsExactlyInAnyOrder(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.BIOS, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIBIOS.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.emptyList());

        List<DMIBIOS> result = service.get(15);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.BIOS, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIBIOS.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
