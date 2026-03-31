/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBaseboard;
import io.github.eggy03.dmidecode.mapper.board.DMIBaseboardMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

/**
 * Service class for fetching baseboard/motherboard information from the system.
 * <p>
 * This class executes the {@link DMIType#BASEBOARD} {@code dmidecode} command
 * and maps the resulting output into a {@link DMIBaseboard} object.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 *
 * DMIBaseboardService service = new DMIBaseboardService();
 * Optional<DMIBaseboard> baseboard = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 */
public class DMIBaseboardService implements OptionalCommonDMIServiceInterface<DMIBaseboard> {

    /**
     * Retrieves baseboard information present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return an {@link Optional} containing {@link DMIBaseboard} information if present,
     *         or {@link Optional#empty()} if no baseboard entry is detected
     *
     * @since 0.1.0
     */
    @Override
    public @NonNull Optional<DMIBaseboard> get(long timeout) {
        return new DMIBaseboardMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommandFor(DMIType.BASEBOARD), timeout),
                DMIBaseboard.class
        );
    }
}