package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.entity.board.DMIBaseboard;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIBaseboardServiceTest {

    private final DMIBaseboardService service = new DMIBaseboardService();

    private static String rawDMIOutput;

    private static DMIBaseboard mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "Base Board Information",
                "\tManufacturer: ASUSTeK COMPUTER INC.",
                "\tProduct Name: PRIME B550M-A",
                "\tVersion: Rev X.0x",
                "\tSerial Number: ABC123456",
                "\tAsset Tag: Default string",
                "\tFeatures:",
                    "\t\tBoard is a hosting board",
                    "\t\tBoard is replaceable",
                "\tLocation In Chassis: Default string",
                "\tChassis Handle: 0x0003",
                "\tType: Motherboard",
                "\tContained Object Handles: 0"
                );


        mockDMIClass = new DMIBaseboard.Builder()
                .manufacturer("ASUSTeK COMPUTER INC.")
                .productName("PRIME B550M-A")
                .version("Rev X.0x")
                .serialNumber("ABC123456")
                .assetTag("Default string")
                .features(Arrays.asList(
                        "Board is a hosting board",
                        "Board is replaceable"
                ))
                .locationInChassis("Default string")
                .chassisHandle("0x0003")
                .type("Motherboard")
                .containedObjectHandles(0)
                .build();
    }


    @Test
    void test_get_success() {

        try(MockedStatic<TerminalUtility> mockTerminal = mockStatic(TerminalUtility.class)){

            mockTerminal
                    .when(()-> TerminalUtility.executeCommand(anyString(), anyLong()))
                    .thenReturn(rawDMIOutput);


            assertThat(service.get(15)).contains(mockDMIClass);

        }
    }

    @Test
    void test_get_fail_invalidRawDmi_emptyOutput() {

        try(MockedStatic<TerminalUtility> mockTerminal = mockStatic(TerminalUtility.class)){

            mockTerminal
                    .when(()-> TerminalUtility.executeCommand(anyString(), anyLong()))
                    .thenReturn("invalid output");


            assertThat(service.get(15)).contains(new DMIBaseboard.Builder().build());

        }
    }
}
