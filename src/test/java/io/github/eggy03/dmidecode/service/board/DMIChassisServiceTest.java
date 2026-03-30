package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.entity.board.DMIChassis;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIChassisServiceTest {

    private final DMIChassisService service = new DMIChassisService();

    private static String rawDMIOutput;

    private static DMIChassis mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "Chassis Information",
                "\tManufacturer: Dell Inc.",
                "\tType: Desktop",
                "\tLock: Not Present",
                "\tVersion: 1.0",
                "\tSerial Number: ABC123456",
                "\tAsset Tag: OFFICE-PC-01",
                "\tBoot-up State: Safe",
                "\tPower Supply State: Safe",
                "\tThermal State: Safe",
                "\tSecurity Status: None",
                "\tOEM Information: N/A",
                "\tHeight: Unspecified",
                "\tNumber Of Power Cords: 1",
                "\tContained Elements: 0",
                "\tSKU Number: SKU-001"
        );

        mockDMIClass = new DMIChassis.Builder()
                .manufacturer("Dell Inc.")
                .type("Desktop")
                .lock("Not Present")
                .version("1.0")
                .serialNumber("ABC123456")
                .assetTag("OFFICE-PC-01")
                .bootUpState("Safe")
                .powerSupplyState("Safe")
                .thermalState("Safe")
                .securityStatus("None")
                .oemInformation("N/A")
                .height("Unspecified")
                .numberOfPowerCords(1)
                .containedElements(0)
                .skuNumber("SKU-001")
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

            assertThat(service.get(15)).contains(new DMIChassis.Builder().build());
        }
    }
}
