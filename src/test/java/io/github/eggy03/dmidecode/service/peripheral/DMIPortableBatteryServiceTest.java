package io.github.eggy03.dmidecode.service.peripheral;

import io.github.eggy03.dmidecode.entity.peripheral.DMIPortableBattery;
import io.github.eggy03.dmidecode.entity.peripheral.ImmutableDMIPortableBattery;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIPortableBatteryServiceTest {

    private final DMIPortableBatteryService service = new DMIPortableBatteryService();

    private static String rawDMIOutput;

    private static DMIPortableBattery mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "Portable Battery",
                "\tLocation: Internal Battery",
                "\tManufacturer: LG",
                "\tName: BAT0",
                "\tDesign Capacity: 50000 mWh",
                "\tDesign Voltage: 11.4 V",
                "\tSBDS Version: 1.1",
                "\tMaximum Error: 2%",
                "\tSBDS Serial Number: 1234",
                "\tSBDS Manufacture Date: 2023-06-01",
                "\tSBDS Chemistry: Li-ion",
                "\tOEM-specific Information: None"
        );

        mockDMIClass = new ImmutableDMIPortableBattery.Builder()
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
    }

    @Test
    void test_get_success() {

        try (MockedStatic<TerminalUtility> mockTerminal = mockStatic(TerminalUtility.class)) {

            mockTerminal
                    .when(() -> TerminalUtility.executeCommand(anyString(), anyLong()))
                    .thenReturn(rawDMIOutput);

            assertThat(service.get(15)).contains(mockDMIClass);
        }
    }

    @Test
    void test_get_fail_invalidRawDmi_emptyOutput() {

        try (MockedStatic<TerminalUtility> mockTerminal = mockStatic(TerminalUtility.class)) {

            mockTerminal
                    .when(() -> TerminalUtility.executeCommand(anyString(), anyLong()))
                    .thenReturn("invalid output");

            assertThat(service.get(15)).isEmpty();
        }
    }
}
