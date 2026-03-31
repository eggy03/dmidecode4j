/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.exception;

import lombok.experimental.StandardException;

/**
 * Thrown when the terminal fails to execute a command or a script
 * @since 0.1.0
 */
@StandardException
public class TerminalExecutionException extends RuntimeException {

}
