package io.github.eggy03.dmidecode.service.processor;

import io.github.eggy03.dmidecode.entity.processor.DMIProcessor;
import io.github.eggy03.dmidecode.entity.processor.ImmutableDMIProcessor;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIProcessorServiceTest {

    private final DMIProcessorService service = new DMIProcessorService();

    private static String rawDMIOutput;

    private static DMIProcessor mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "Processor Information",
                "\tSocket Designation: CPU0",
                "\tType: Central Processor",
                "\tFamily: Core i7",
                "\tManufacturer: Intel",
                "\tID: BFEBFBFF000806EA",
                "\tSignature: Type 0, Family 6, Model 154, Stepping 3",
                "\tFlags:",
                "\t\tfpu",
                "\t\tvme",
                "\t\tsse",
                "\t\tsse2",
                "\tVersion: Intel(R) Core(TM) i7-12700H",
                "\tVoltage: 1.2 V",
                "\tExternal Clock: 100 MHz",
                "\tMax Speed: 4700 MHz",
                "\tCurrent Speed: 2700 MHz",
                "\tStatus: Populated, Enabled",
                "\tUpgrade: Socket BGA1744",
                "\tL1 Cache Handle: 0x0007",
                "\tL2 Cache Handle: 0x0008",
                "\tL3 Cache Handle: 0x0009",
                "\tSerial Number: To Be Filled By O.E.M.",
                "\tAsset Tag: Not Specified",
                "\tPart Number: Not Specified",
                "\tCore Count: 14",
                "\tCore Enabled: 14",
                "\tThread Count: 20",
                "\tCharacteristics:",
                "\t\t64-bit capable",
                "\t\tMulti-Core",
                "\t\tHardware Thread"
        );

        mockDMIClass = new ImmutableDMIProcessor.Builder()
                .socketDesignation("CPU0")
                .type("Central Processor")
                .family("Core i7")
                .manufacturer("Intel")
                .id("BFEBFBFF000806EA")
                .signature("Type 0, Family 6, Model 154, Stepping 3")
                .flags(Arrays.asList(
                        "fpu",
                        "vme",
                        "sse",
                        "sse2"
                ))
                .version("Intel(R) Core(TM) i7-12700H")
                .voltage("1.2 V")
                .externalClock("100 MHz")
                .maxSpeed("4700 MHz")
                .currentSpeed("2700 MHz")
                .status("Populated, Enabled")
                .upgrade("Socket BGA1744")
                .l1CacheHandle("0x0007")
                .l2CacheHandle("0x0008")
                .l3CacheHandle("0x0009")
                .serialNumber("To Be Filled By O.E.M.")
                .assetTag("Not Specified")
                .partNumber("Not Specified")
                .coreCount(14)
                .coreEnabled(14)
                .threadCount(20)
                .characteristics(Arrays.asList(
                        "64-bit capable",
                        "Multi-Core",
                        "Hardware Thread"
                ))
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
