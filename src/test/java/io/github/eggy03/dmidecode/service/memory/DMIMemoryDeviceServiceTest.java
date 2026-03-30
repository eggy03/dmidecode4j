package io.github.eggy03.dmidecode.service.memory;

import io.github.eggy03.dmidecode.entity.memory.DMIMemoryDevice;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIMemoryDeviceServiceTest {

    private final DMIMemoryDeviceService service = new DMIMemoryDeviceService();

    private static String rawDMIOutput;

    private static DMIMemoryDevice mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "Memory Device",
                "\tArray Handle: 0x002C",
                "\tError Information Handle: 0x002D",
                "\tTotal Width: 64 bits",
                "\tData Width: 64 bits",
                "\tSize: 16 GB",
                "\tForm Factor: DIMM",
                "\tSet: None",
                "\tLocator: DIMM_A1",
                "\tBank Locator: BANK 0",
                "\tType: DDR4",
                "\tType Detail: Synchronous",
                "\tSpeed: 3200 MT/s",
                "\tManufacturer: Samsung",
                "\tSerial Number: 12345678",
                "\tAsset Tag: Not Specified",
                "\tPart Number: M378A2K43CB1-CTD",
                "\tRank: 2",
                "\tConfigured Memory Speed: 2933 MT/s",
                "\tMinimum Voltage: 1.2 V",
                "\tMaximum Voltage: 1.2 V",
                "\tConfigured Voltage: 1.2 V"
        );

        mockDMIClass = new DMIMemoryDevice.Builder()
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
