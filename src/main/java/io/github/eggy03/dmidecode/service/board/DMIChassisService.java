/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIChassis;
import io.github.eggy03.dmidecode.mapper.board.DMIChassisMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

/**
 * Service class for fetching chassis information from the system.
 * <p>
 * This class executes the {@link DMIType#CHASSIS} {@code dmidecode} command
 * and maps the resulting output into a {@link DMIChassis} object.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIChassisService service = new DMIChassisService();
 * Optional<DMIChassis> chassis = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 */
public class DMIChassisService implements OptionalCommonDMIServiceInterface<DMIChassis> {

    /**
     * Retrieves chassis information present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return an {@link Optional} containing {@link DMIChassis} information if present,
     *         or {@link Optional#empty()} if no chassis entry is detected
     *
     * @since 0.1.0
     */
    @Override
    public @NonNull Optional<DMIChassis> get(long timeout) {
        return new DMIChassisMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommandFor(DMIType.CHASSIS), timeout),
                DMIChassis.class
        );
    }
}
