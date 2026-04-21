package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.entity.board.DMIBIOS;
import io.github.eggy03.dmidecode.entity.board.ImmutableDMIBIOS;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIBIOSServiceTest {

    private final DMIBIOSService service = new DMIBIOSService();

    private static String rawDMIOutput;

    private static DMIBIOS mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "BIOS Information",
                "\tVendor: American Megatrends Inc.",
                "\tVersion: F10",
                "\tRelease Date: 07/15/2023",
                "\tAddress: 0xF0000",
                "\tRuntime Size: 64 kB",
                "\tROM Size: 16 MB",
                "\tCharacteristics:",
                "\t\tPCI is supported",
                "\t\tBIOS is upgradeable",
                "\tBIOS Revision: 5.17",
                "\tFirmware Revision: 1.12"
        );

        mockDMIClass = new ImmutableDMIBIOS.Builder()
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
