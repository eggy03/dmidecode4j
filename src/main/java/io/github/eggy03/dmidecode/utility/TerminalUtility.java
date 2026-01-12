/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.utility;

import io.github.eggy03.dmidecode.exception.TerminalExecutionException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;

/**
 * A utility class that provides a way to launch a terminal session
 * <p>
 * <b>Mostly for internal use </b>
 * @author Sayan Bhattacharya
 * @since 0.1.0
 */
@UtilityClass
@Slf4j
public class TerminalUtility {

    /**
     * Launches a standalone terminal session, executes the given command
     * and returns the result
     *
     * @param command The command/script to be executed in the terminal
     * @param timeoutSeconds Time in seconds after which the session will be force stopped
     * @return The result of the command executed
     * @throws TerminalExecutionException When the process is killed pre-maturely upon reaching the timeout
     *                                   or when the command yields an error, or when the terminal cannot be accessed.
     * @throws IllegalArgumentException If the provided timeout is in the negative
     */
    public static String executeCommand(@NotNull String command, long timeoutSeconds) {

        if(timeoutSeconds<0)
            throw new IllegalArgumentException("Timeout cannot be negative");

        CommandLine cmdLine = new CommandLine("bash");
        cmdLine.addArgument("-c");
        cmdLine.addArgument(command, false);

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();

        ExecuteWatchdog watchdog = ExecuteWatchdog.builder().setTimeout(Duration.ofSeconds(timeoutSeconds)).get();

        DefaultExecutor executor = DefaultExecutor.builder().get();
        executor.setStreamHandler(new PumpStreamHandler(result, err));
        executor.setWatchdog(watchdog);

        try {
            int exitCode = executor.execute(cmdLine);
            log.debug("\nCommand Executed: {}\nExit code: {}\nError Stream: {}\nResult Stream: {}\n", command, exitCode, err, result);
            return result.toString();
        } catch (ExecuteException e) {
            String reason = watchdog.killedProcess() ?
                    "\nProcess executing the following command: " + command + "\nWas killed after a timeout of " + timeoutSeconds + " seconds\n" :
                    "\nProcess executing the following command: " + command + "\nExited with a non-zero exit code\nTerminal Error Output: "+ err;

            throw new TerminalExecutionException(reason, e);
        } catch (IOException e) {
            String reason = "An I/O Exception occurred during executing the following command:\n" + command;
            throw new TerminalExecutionException(reason, e);
        }
    }
}
