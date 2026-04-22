package io.github.eggy03.dmidecode.terminal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@EnabledOnOs(OS.LINUX)
class TerminalServiceTest {

    private static final long TIMEOUT = 15L;
    private final TerminalService terminalService = new TerminalService();

    @Test
    void testValidCommand() {
        String validCommand = "echo $((10+20))";
        TerminalResult result = terminalService.execute(validCommand, TIMEOUT);

        assertThat(result.getResult()).isEqualTo("30" + System.lineSeparator());
        assertThat(result.getError()).isEmpty();
    }

    @Test
    void testInvalidCommand() {
        TerminalResult result = terminalService.execute("invalidCommand", TIMEOUT);
        assertThat(result.getResult()).isEmpty();
        assertThat(result.getError()).isNotEmpty();
    }

    @Test
    void testValidScript() {
        String validScript = "a=10\n" + "a=$((a+1))\n" + "echo $a";
        TerminalResult result = terminalService.execute(validScript, TIMEOUT);

        assertThat(result.getResult()).isEqualTo("11" + System.lineSeparator());
        assertThat(result.getError()).isEmpty();
    }

    @Test
    void testValidScriptFromResource() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                TerminalServiceTest.class.getResourceAsStream("/simple_addition.sh")
                        )
                )
        );

        String script = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        TerminalResult result = terminalService.execute(script, TIMEOUT);

        assertThat(result.getResult()).isEqualTo("3" + System.lineSeparator());
        assertThat(result.getError()).isEmpty();
    }

    @Test
    void testTimeout() {
        String sleepCommand = "sleep 30";

        TerminalResult result = terminalService.execute(sleepCommand, 1);

        assertThat(result.getResult()).isEmpty();
        assertThat(result.getError()).isEmpty();
    }


    @Test
    void testMixedOutput() {
        String mixedCommand = "echo hi; invalidCmd";

        TerminalResult result = terminalService.execute(mixedCommand, TIMEOUT);

        assertThat(result.getResult()).isEqualTo("hi" + System.lineSeparator());
        assertThat(result.getError()).isNotEmpty();
    }

    @Test
    void testNegativeTimeout() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> terminalService.execute("echo Hello", -1)
        );

        assertThat(ex.getMessage()).isEqualTo("Timeout cannot be negative");
    }
}

