package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.entity.board.DMISystemSlots;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMISystemSlotsServiceTest {

    private final DMISystemSlotsService service = new DMISystemSlotsService();

    private static String rawDMIOutput;

    private static DMISystemSlots mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "System Slot Information",
                "\tDesignation: PCIEX16",
                "\tType: PCI Express",
                "\tCurrent Usage: In Use",
                "\tLength: Long",
                "\tID: 1",
                "\tCharacteristics:",
                "\t\t3.3 V is provided",
                "\t\tPME signal is supported",
                "\tBus Address: 0000:01:00.0"
        );

        mockDMIClass = new DMISystemSlots.Builder()
                .designation("PCIEX16")
                .type("PCI Express")
                .currentUsage("In Use")
                .length("Long")
                .id(1)
                .characteristics(Arrays.asList(
                        "3.3 V is provided",
                        "PME signal is supported"
                ))
                .busAddress("0000:01:00.0")
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
