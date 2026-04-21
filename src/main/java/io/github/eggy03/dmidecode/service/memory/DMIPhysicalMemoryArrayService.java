/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.memory;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.memory.DMIPhysicalMemoryArray;
import io.github.eggy03.dmidecode.mapper.physicalmemory.DMIPhysicalMemoryArrayMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Service class for fetching physical memory array information from the system.
 * <p>
 * This class executes the {@link DMIType#PHYSICAL_MEMORY_ARRAY} {@code dmidecode} command
 * and maps the resulting output into a {@link DMIPhysicalMemoryArray} object.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIPhysicalMemoryArrayService service = new DMIPhysicalMemoryArrayService();
 * Optional<DMIPhysicalMemoryArray> memoryArray = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 */
public class DMIPhysicalMemoryArrayService implements OptionalCommonDMIServiceInterface<DMIPhysicalMemoryArray> {

    private final TerminalService terminalService;
    private final DMIPhysicalMemoryArrayMapper mapper;

    /**
     * Creates {@link DMIPhysicalMemoryArrayService} with default configuration.
     *
     * @since 0.3.0
     */
    public DMIPhysicalMemoryArrayService() {
        this(new TerminalService(), new DMIPhysicalMemoryArrayMapper());
    }

    /**
     * Package Private constructor with injectable dependencies
     *
     * @param terminalService the {@link TerminalService} instance to use, must not be {@code null}
     * @param mapper          the mapper instance to use, must not be {@code null}
     * @since 0.3.0
     */
    DMIPhysicalMemoryArrayService(@NonNull TerminalService terminalService, @NonNull DMIPhysicalMemoryArrayMapper mapper) {
        this.terminalService = Objects.requireNonNull(terminalService, "terminalService cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

    /**
     * Retrieves physical memory array information present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return an {@link Optional} containing {@link DMIPhysicalMemoryArray} information
     * if present, or {@link Optional#empty()} if no physical memory array
     * entry is detected
     * @since 0.1.0
     */
    @Override
    public @NonNull Optional<DMIPhysicalMemoryArray> get(long timeout) {

        TerminalResult result = terminalService.executeCommand(DMIType.PHYSICAL_MEMORY_ARRAY, timeout);
        return mapper.mapToEntity(result.getResult(), DMIPhysicalMemoryArray.class);
    }
}
