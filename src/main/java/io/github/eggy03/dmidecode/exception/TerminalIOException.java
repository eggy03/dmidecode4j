/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.exception;

/**
 * Thrown when the terminal fails to execute a command or a script
 *
 * @since 0.1.0
 */
public class TerminalIOException extends RuntimeException {

    @SuppressWarnings("unused")
    public TerminalIOException(String message, Throwable cause) {
        super(message, cause);
    }

    @SuppressWarnings("unused")
    public TerminalIOException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public TerminalIOException(Throwable cause) {
        super("Terminal Execution Failure", cause);
    }

}
