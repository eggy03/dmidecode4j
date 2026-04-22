package io.github.eggy03.dmidecode.service.system;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.system.DMISystem;
import io.github.eggy03.dmidecode.entity.system.ImmutableDMISystem;
import io.github.eggy03.dmidecode.mapper.system.DMISystemMapper;
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
class DMISystemServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMISystem mockDMIClass = new ImmutableDMISystem.Builder()
            .manufacturer("LENOVO")
            .productName("ThinkPad T14 Gen 3")
            .version("ThinkPad T14 Gen 3")
            .serialNumber("PF123ABC")
            .uuid("4C4C4544-0038-4D10-8051-CAC04F4A1234")
            .wakeupType("Power Switch")
            .skuNumber("21CFCTO1WW")
            .family("ThinkPad")
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMISystemMapper mapper;
    @InjectMocks
    private DMISystemService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToEntity(anyString(), any())).thenReturn(Optional.of(mockDMIClass));

        Optional<DMISystem> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).contains(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.SYSTEM, 15);
        inOrder.verify(mapper).mapToEntity(validTerminalResult.getResult(), DMISystem.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToEntity(anyString(), any())).thenReturn(Optional.empty());

        Optional<DMISystem> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.SYSTEM, 10);
        inOrder.verify(mapper).mapToEntity(validTerminalResult.getResult(), DMISystem.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
