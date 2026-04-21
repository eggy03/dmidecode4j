package io.github.eggy03.dmidecode.service.system;

import io.github.eggy03.dmidecode.entity.system.DMISystem;
import io.github.eggy03.dmidecode.entity.system.ImmutableDMISystem;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMISystemServiceTest {

    private final DMISystemService service = new DMISystemService();

    private static String rawDMIOutput;

    private static DMISystem mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "System Information",
                "\tManufacturer: LENOVO",
                "\tProduct Name: ThinkPad T14 Gen 3",
                "\tVersion: ThinkPad T14 Gen 3",
                "\tSerial Number: PF123ABC",
                "\tUUID: 4C4C4544-0038-4D10-8051-CAC04F4A1234",
                "\tWake-up Type: Power Switch",
                "\tSKU Number: 21CFCTO1WW",
                "\tFamily: ThinkPad"
        );

        mockDMIClass = new ImmutableDMISystem.Builder()
                .manufacturer("LENOVO")
                .productName("ThinkPad T14 Gen 3")
                .version("ThinkPad T14 Gen 3")
                .serialNumber("PF123ABC")
                .uuid("4C4C4544-0038-4D10-8051-CAC04F4A1234")
                .wakeupType("Power Switch")
                .skuNumber("21CFCTO1WW")
                .family("ThinkPad")
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

            assertThat(service.get(15)).contains(new ImmutableDMISystem.Builder().build());
        }
    }
}
