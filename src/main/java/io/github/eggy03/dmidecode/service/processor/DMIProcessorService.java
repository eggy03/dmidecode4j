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
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;

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

    private final TerminalService terminalService;
    private final DMIProcessorMapper mapper;

    /**
     * Creates {@link DMIProcessorService} with default configuration.
     *
     * @since 0.3.0
     */
    public DMIProcessorService() {
        this(new TerminalService(), new DMIProcessorMapper());
    }

    /**
     * Package Private constructor with injectable dependencies
     *
     * @param terminalService the {@link TerminalService} instance to use, must not be {@code null}
     * @param mapper          the mapper instance to use, must not be {@code null}
     * @since 0.3.0
     */
    DMIProcessorService(@NonNull TerminalService terminalService, @NonNull DMIProcessorMapper mapper) {
        this.terminalService = Objects.requireNonNull(terminalService, "terminalService cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

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
     * if present, or an empty list if no processor entries are detected
     * @since 0.1.0
     */
    @Override
    public @Unmodifiable @NonNull List<DMIProcessor> get(long timeout) {

        TerminalResult result = terminalService.executeCommand(DMIType.PROCESSOR, timeout);
        return mapper.mapToList(result.getResult(), DMIProcessor.class);
    }

}
