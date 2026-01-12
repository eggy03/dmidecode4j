package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.entity.board.DMIBIOSLanguage;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DMIBIOSLanguageServiceTest {

    private final DMIBIOSLanguageService service = new DMIBIOSLanguageService();

    private static String rawDMIOutput;

    private static DMIBIOSLanguage mockDMIClass;

    @BeforeAll
    static void setup() {
        rawDMIOutput = String.join(
                System.lineSeparator(),
                "BIOS Language Information",
                "\tInstallable Languages:",
                "\t\ten|US",
                "\t\tfr|FR",
                "\t\tde|DE",
                "\tCurrently Installed Language: en|US"
        );

        mockDMIClass = DMIBIOSLanguage.builder()
                .installableLanguages(Arrays.asList(
                        "en|US",
                        "fr|FR",
                        "de|DE"
                ))
                .currentLanguage("en|US")
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

            assertThat(service.get(15)).contains(DMIBIOSLanguage.builder().build());
        }
    }
}
