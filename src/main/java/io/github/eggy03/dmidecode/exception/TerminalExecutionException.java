/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.exception;

/**
 * Thrown when the terminal fails to execute a command or a script
 * @since 0.1.0
 */
public class TerminalExecutionException extends RuntimeException {

    @SuppressWarnings("unused")
    public TerminalExecutionException(String message, Throwable cause){
        super(message, cause);
    }

    @SuppressWarnings("unused")
    public TerminalExecutionException(String message){
        super(message);
    }

    @SuppressWarnings("unused")
    public TerminalExecutionException(Throwable cause){
        super("Terminal Execution Failure", cause);
    }

}
