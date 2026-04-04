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
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jspecify.annotations.NonNull;

import java.util.List;

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
        return new DMICacheMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommandFor(DMIType.CACHE), timeout),
                DMICache.class
        );
    }
}
