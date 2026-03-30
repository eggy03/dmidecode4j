package io.github.eggy03.dmidecode.service.processor;

import io.github.eggy03.dmidecode.entity.processor.DMICache;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMICacheServiceTest {

    private final DMICacheService service = new DMICacheService();

    private static String rawDMIOutput;

    private static DMICache mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "Cache Information",
                "\tSocket Designation: L3-Cache",
                "\tConfiguration: Enabled, Not Socketed, Level 3",
                "\tOperational Mode: Write Back",
                "\tLocation: Internal",
                "\tInstalled Size: 32 MB",
                "\tMaximum Size: 64 MB",
                "\tSupported SRAM Types:",
                "\t\tPipeline Burst",
                "\t\tSynchronous",
                "\tInstalled SRAM Type: Pipeline Burst",
                "\tSpeed: Unknown",
                "\tError Correction Type: Multi-bit ECC",
                "\tSystem Type: Unified",
                "\tAssociativity: 16-way Set-Associative"
        );

        mockDMIClass = new DMICache.Builder()
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
