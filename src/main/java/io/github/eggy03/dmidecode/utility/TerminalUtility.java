package io.github.eggy03.dmidecode.utility;

import io.github.eggy03.dmidecode.exception.TerminalExecutionException;
import io.github.eggy03.dmidecode.exception.TerminalTimeoutException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;

@UtilityClass
@Slf4j
public class TerminalUtility {

    public static String executeCommand(String command, long timeoutSeconds) {

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
            executor.execute(cmdLine);
            return !err.toString().isEmpty() ? err.toString() : result.toString();
        } catch (ExecuteException e) {
            if (watchdog.killedProcess()) {
                throw new TerminalTimeoutException("Timed out after " + timeoutSeconds + " seconds while executing the following command:\n" + command, e);
            } else {
                log.debug("Execution Failure! Process executing the following command:\n{}\n did not finish executing properly", command);
                throw new TerminalExecutionException(err.toString(), e);
            }
        } catch (IOException e) {
            log.debug("An I/O Exception occurred during executing the following command:\n{}", command);
            throw new TerminalExecutionException(err.toString(), e);
        }
    }
}
