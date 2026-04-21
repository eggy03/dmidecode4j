/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.system;

import io.github.eggy03.dmidecode.annotation.Unmodifiable;
import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.system.DMISystem;
import io.github.eggy03.dmidecode.mapper.system.DMISystemMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Service class for fetching system information from the system.
 * <p>
 * This class executes the {@link DMIType#SYSTEM} {@code dmidecode} command
 * and maps the resulting output into a {@link DMISystem} object.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMISystemService service = new DMISystemService();
 * Optional<DMISystem> system = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 */
public class DMISystemService implements OptionalCommonDMIServiceInterface<DMISystem> {

    private final TerminalService terminalService;
    private final DMISystemMapper mapper;

    /**
     * Creates {@link DMISystemService} with default configuration.
     *
     * @since 0.3.0
     */
    public DMISystemService() {
        this(new TerminalService(), new DMISystemMapper());
    }

    /**
     * Package Private constructor with injectable dependencies
     *
     * @param terminalService the {@link TerminalService} instance to use, must not be {@code null}
     * @param mapper          the mapper instance to use, must not be {@code null}
     * @since 0.3.0
     */
    DMISystemService(@NonNull TerminalService terminalService, @NonNull DMISystemMapper mapper) {
        this.terminalService = Objects.requireNonNull(terminalService, "terminalService cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

    /**
     * Retrieves system information present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return an {@link Optional} containing {@link DMISystem} information
     * if present, or {@link Optional#empty()} if no system entry
     * is detected
     * @since 0.1.0
     */
    @Override
    public @Unmodifiable @NonNull Optional<DMISystem> get(long timeout) {

        TerminalResult result = terminalService.executeCommand(DMIType.SYSTEM, timeout);
        return mapper.mapToEntity(result.getResult(), DMISystem.class);
    }
}
