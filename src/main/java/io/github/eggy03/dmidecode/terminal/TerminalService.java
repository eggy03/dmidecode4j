/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.terminal;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.exception.TerminalIOException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

/**
 * A service class that provides a way to launch a terminal session
 * <p>
 * <b>for internal use </b>
 *
 * @since 0.1.0
 */
public class TerminalService {

    private static final Logger log = LoggerFactory.getLogger(TerminalService.class);

    /**
     * Launches a standalone PowerShell session, executes {@link DMIType#getCommand()} and returns the result
     *
     * @param dmiType        The non-null enum value containing the command which shall be executed
     * @param timeoutSeconds The non-null, positive value of time in seconds after which the session will be force stopped.
     * @return The result of the query executed, wrapped in {@link TerminalResult}
     * @since 0.3.0
     */
    @NonNull
    public TerminalResult executeCommand(@NonNull DMIType dmiType, long timeoutSeconds) {
        Objects.requireNonNull(dmiType, "dmiType cannot be null");
        return execute(dmiType.getCommand(), timeoutSeconds);
    }


    /**
     * Launches a standalone Terminal session and executes commands and returns the result
     *
     * @param command The command to be executed in the Terminal, must not be null
     * @param timeoutSeconds Time in seconds after which the session will be force stopped, must not be null.
     * @return The result of the command executed, wrapped in {@link TerminalResult}
     * @throws IllegalArgumentException if timeout is in negative.
     * @since 0.1.0
     */
    @NonNull TerminalResult execute(@NonNull String command, long timeoutSeconds) {

        Objects.requireNonNull(command, "command or script to be executed cannot be null");

        if (timeoutSeconds < 0)
            throw new IllegalArgumentException("Timeout cannot be negative");

        CommandLine cmdLine = new CommandLine("bash");
        cmdLine.addArgument("-c");
        cmdLine.addArgument(command, false);

        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

        ExecuteWatchdog watchdog = ExecuteWatchdog.builder().setTimeout(Duration.ofSeconds(timeoutSeconds)).get();

        DefaultExecutor executor = DefaultExecutor.builder().get();
        executor.setStreamHandler(new PumpStreamHandler(resultStream, errorStream));
        executor.setWatchdog(watchdog);

        try {
            int exitCode = executor.execute(cmdLine);
            log.debug("\nTerminal Execution - SUCCESS\nExit code: {}\nCommand: {}\nStdout: {}\nStderr: {}\n", exitCode, command, resultStream, errorStream);
            return new TerminalResult(resultStream.toString(), errorStream.toString());
        } catch (ExecuteException e) {

            boolean processKilled = watchdog.killedProcess();
            if (log.isDebugEnabled())
                log.debug("\nTerminal Execution - FAILURE\nProcess Killed: {}\nTimeout: {}\nCommand: {}\nStdout: {}\nStderr: {}\n", processKilled, timeoutSeconds, command, resultStream, errorStream, e);
            else
                log.warn("\nTerminal Execution - FAILURE\nProcess Killed: {}\nEnable DEBUG mode to see the commands it tried to execute\n", processKilled, e);

            return new TerminalResult(resultStream.toString(), errorStream.toString());

        } catch (IOException e) {
            throw new TerminalIOException("An I/O Exception occurred while running Terminal", e);
        }
    }
}
