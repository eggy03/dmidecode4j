/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.processor;

import io.github.eggy03.dmidecode.annotation.Unmodifiable;
import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.processor.DMICache;
import io.github.eggy03.dmidecode.mapper.processor.DMICacheMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;

/**
 * Service class for fetching cache information from the system.
 * <p>
 * This class executes the {@link DMIType#CACHE} {@code dmidecode} command
 * and maps the resulting output into {@link DMICache} objects.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMICacheService service = new DMICacheService();
 * List<DMICache> caches = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 */
public class DMICacheService implements CommonDMIServiceInterface<DMICache> {

    private final TerminalService terminalService;
    private final DMICacheMapper mapper;

    /**
     * Creates {@link DMICacheService} with default configuration.
     *
     * @since 0.3.0
     */
    public DMICacheService() {
        this(new TerminalService(), new DMICacheMapper());
    }

    /**
     * Package Private constructor with injectable dependencies
     *
     * @param terminalService the {@link TerminalService} instance to use, must not be {@code null}
     * @param mapper          the mapper instance to use, must not be {@code null}
     * @since 0.3.0
     */
    DMICacheService(@NonNull TerminalService terminalService, @NonNull DMICacheMapper mapper) {
        this.terminalService = Objects.requireNonNull(terminalService, "terminalService cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

    /**
     * Retrieves cache entries present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return a list of {@link DMICache} objects representing
     * the system cache entries.
     * Returns an empty list if no cache entries are detected.
     * @since 0.1.0
     */
    @Override
    public @Unmodifiable @NonNull List<DMICache> get(long timeout) {

        TerminalResult result = terminalService.executeCommand(DMIType.CACHE, timeout);
        return mapper.mapToList(result.getResult(), DMICache.class);
    }
}
