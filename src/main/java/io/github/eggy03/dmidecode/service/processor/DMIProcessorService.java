/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.processor;

import io.github.eggy03.dmidecode.annotation.Unmodifiable;
import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.processor.DMIProcessor;
import io.github.eggy03.dmidecode.mapper.processor.DMIProcessorMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jspecify.annotations.NonNull;

import java.util.List;

/**
 * Service class for fetching processor information from the system.
 * <p>
 * This class executes the {@link DMIType#PROCESSOR} {@code dmidecode} command
 * and maps the resulting output into a {@link DMIProcessor} object.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIProcessorService service = new DMIProcessorService();
 * Optional<DMIProcessor> processor = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 */
public class DMIProcessorService implements CommonDMIServiceInterface<DMIProcessor> {

    /**
     * Retrieves processor information present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return a list containing {@link DMIProcessor} entries
     *         if present, or an empty list if no processor entries are detected
     *
     * @since 0.1.0
     */
    @Override
    public @Unmodifiable @NonNull List<DMIProcessor> get(long timeout) {
        return new DMIProcessorMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PROCESSOR.getValue()), timeout),
                DMIProcessor.class
        );
    }

}
