/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.system;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.system.DMISystem;
import io.github.eggy03.dmidecode.mapper.system.DMISystemMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

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
 * @author Sayan Bhattacharya
 */
public class DMISystemService implements OptionalCommonDMIServiceInterface<DMISystem> {

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
     *         if present, or {@link Optional#empty()} if no system entry
     *         is detected
     *
     * @since 0.1.0
     */
    @Override
    @NotNull
    public Optional<DMISystem> get(long timeout) {
        return new DMISystemMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.SYSTEM.getValue()), timeout),
                DMISystem.class
        );
    }
}
