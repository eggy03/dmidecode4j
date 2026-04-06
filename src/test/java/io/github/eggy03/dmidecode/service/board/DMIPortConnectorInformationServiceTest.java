package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.entity.board.DMIPortConnectorInformation;

import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIPortConnectorInformationServiceTest {

    private final DMIPortConnectorInformationService service = new DMIPortConnectorInformationService();

    private static String rawDMIOutput;

    private static DMIPortConnectorInformation mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "Port Connector Information",
                "\tExternal Reference Designator: USB1",
                "\tInternal Reference Designator: JUSB1",
                "\tExternal Connector Type: USB",
                "\tInternal Connector Type: USB",
                "\tPort Type: USB"
        );

        mockDMIClass = new DMIPortConnectorInformation.Builder()
                .externalReferenceDesignator("USB1")
                .internalReferenceDesignator("JUSB1")
                .externalConnectorType("USB")
                .internalConnectorType("USB")
                .portType("USB")
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

