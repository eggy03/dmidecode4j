package io.github.eggy03.dmidecode.utility;

import io.github.eggy03.dmidecode.exception.TerminalExecutionException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * WARNING:
 * This test requires a Linux system with /bin/sh available.
 * It spawns real shell processes.
 */
class TerminalUtilityTest {

    private static final long TIMEOUT = 15L;

    @Test
    void testValidCommand() {
        String validCommand = "echo $((10+20))";
        String result = TerminalUtility.executeCommand(validCommand, TIMEOUT);

        assertThat(result).isEqualTo("30" + System.lineSeparator());
    }

    @Test
    void testInvalidCommand() {
        assertThrows(
                TerminalExecutionException.class,
                () -> TerminalUtility.executeCommand("invalidCommand", TIMEOUT)
        );
    }

    @Test
    void testValidScript() {
        String validScript = "a=10\n" + "a=$((a+1))\n" + "echo $a";
        String result = TerminalUtility.executeCommand(validScript, TIMEOUT);
        assertThat(result).isEqualTo("11" + System.lineSeparator());
    }

    @Test
    void testValidScriptFromResource() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                TerminalUtilityTest.class.getResourceAsStream("/simple_addition.sh")
                        )
                )
        );

        StringBuilder script = new StringBuilder();
        reader.lines().forEach(line -> script.append(line).append(System.lineSeparator()));

        String result = TerminalUtility.executeCommand(script.toString(), TIMEOUT);

        assertThat(result).isEqualTo("3" + System.lineSeparator());
    }

    @Test
    void testTimeout() {
        String sleepCommand = "sleep 30";

        TerminalExecutionException ex = assertThrows(
                TerminalExecutionException.class,
                () -> TerminalUtility.executeCommand(sleepCommand, 1)
        );

        assertThat(ex.getMessage()).contains("Was killed after a timeout");
    }

    @Test
    void testErrorStream() {
        String errorCommand = "invalid-command";

        TerminalExecutionException ex = assertThrows(
                TerminalExecutionException.class,
                () -> TerminalUtility.executeCommand(errorCommand, TIMEOUT)
        );

        assertThat(ex.getMessage())
                .contains("Terminal Error Output")
                .contains("invalid-command");
    }

    @Test
    void testMixedOutput() {
        String mixedCommand = "echo hi; invalidCmd";

        TerminalExecutionException ex = assertThrows(
                TerminalExecutionException.class,
                () -> TerminalUtility.executeCommand(mixedCommand, TIMEOUT)
        );

        assertThat(ex.getMessage())
                .contains("Terminal Error Output")
                .contains("invalidCmd");
    }

    @Test
    void testNegativeTimeout() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> TerminalUtility.executeCommand("echo Hello", -1)
        );

        assertThat(ex.getMessage()).isEqualTo("Timeout cannot be negative");
    }
}

