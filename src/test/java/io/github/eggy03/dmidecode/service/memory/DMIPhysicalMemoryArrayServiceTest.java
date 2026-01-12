package io.github.eggy03.dmidecode.service.memory;

import io.github.eggy03.dmidecode.entity.memory.DMIPhysicalMemoryArray;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIPhysicalMemoryArrayServiceTest {

    private final DMIPhysicalMemoryArrayService service = new DMIPhysicalMemoryArrayService();

    private static String rawDMIOutput;

    private static DMIPhysicalMemoryArray mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "Physical Memory Array",
                "\tLocation: System Board Or Motherboard",
                "\tUse: System Memory",
                "\tError Correction Type: Multi-bit ECC",
                "\tMaximum Capacity: 128 GB",
                "\tError Information Handle: 0x0030",
                "\tNumber Of Devices: 4"
        );

        mockDMIClass = DMIPhysicalMemoryArray.builder()
                .location("System Board Or Motherboard")
                .use("System Memory")
                .errorCorrectionType("Multi-bit ECC")
                .maximumCapacity("128 GB")
                .errorInformationHandle("0x0030")
                .numberOfDevices(4)
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

            assertThat(service.get(15))
                    .contains(DMIPhysicalMemoryArray.builder().build());
        }
    }
}
