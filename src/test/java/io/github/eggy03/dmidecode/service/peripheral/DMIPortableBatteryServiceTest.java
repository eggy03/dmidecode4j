package io.github.eggy03.dmidecode.service.peripheral;


import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.peripheral.DMIPortableBattery;
import io.github.eggy03.dmidecode.entity.peripheral.ImmutableDMIPortableBattery;
import io.github.eggy03.dmidecode.mapper.peripheral.DMIPortableBatteryMapper;
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
class DMIPortableBatteryServiceTest {

    private final TerminalResult validTerminalResult = new TerminalResult("{}", "");
    private final DMIPortableBattery mockDMIClass = new ImmutableDMIPortableBattery.Builder()
            .location("Internal Battery")
            .manufacturer("LG")
            .name("BAT0")
            .designCapacity("50000 mWh")
            .designVoltage("11.4 V")
            .sbdsVersion("1.1")
            .maximumError("2%")
            .sbdsSerialNumber("1234")
            .sbdsManufactureDate("2023-06-01")
            .sbdsChemistry("Li-ion")
            .oemSpecificInformation("None")
            .build();

    @Mock
    private TerminalService terminalService;
    @Mock
    private DMIPortableBatteryMapper mapper;
    @InjectMocks
    private DMIPortableBatteryService service;

    @Test
    void test_get_returnsMappedResult() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.singletonList(mockDMIClass));

        List<DMIPortableBattery> result = service.get(15);
        // check that the service layer does not perform any unexpected operations on the mapped data
        assertThat(result).containsExactlyInAnyOrder(mockDMIClass);

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.PORTABLE_BATTERY, 15);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIPortableBattery.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }

    @Test
    void test_get_returnsEmptyWhenMapperReturnsEmpty() {

        when(terminalService.executeCommand(any(), anyLong())).thenReturn(validTerminalResult);

        when(mapper.mapToList(anyString(), any())).thenReturn(Collections.emptyList());

        List<DMIPortableBattery> result = service.get(10);
        assertThat(result).isEmpty();

        InOrder inOrder = inOrder(terminalService, mapper);
        inOrder.verify(terminalService).executeCommand(DMIType.PORTABLE_BATTERY, 10);
        inOrder.verify(mapper).mapToList(validTerminalResult.getResult(), DMIPortableBattery.class);

        verifyNoMoreInteractions(terminalService, mapper);
    }
}
