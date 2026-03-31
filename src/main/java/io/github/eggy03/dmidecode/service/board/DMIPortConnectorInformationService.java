/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.annotation.Unmodifiable;
import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIPortConnectorInformation;
import io.github.eggy03.dmidecode.mapper.board.DMIPortConnectionInformationMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jspecify.annotations.NonNull;

import java.util.List;

/**
 * Service class for fetching port connector information from the system.
 * <p>
 * This class executes the {@link DMIType#PORT_CONNECTOR} {@code dmidecode} command
 * and maps the resulting output into {@link DMIPortConnectorInformation} objects.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIPortConnectorInformationService service = new DMIPortConnectorInformationService();
 * List<DMIPortConnectorInformation> ports = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
public class DMIPortConnectorInformationService implements CommonDMIServiceInterface<DMIPortConnectorInformation> {

    /**
     * Retrieves port connector entries present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return a list of {@link DMIPortConnectorInformation} objects representing
     *         the system port connector entries.
     *         Returns an empty list if no port connector entries are detected.
     *
     * @since 0.1.0
     */
    @Override
    public @Unmodifiable @NonNull List<DMIPortConnectorInformation> get(long timeout) {
        return new DMIPortConnectionInformationMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PORT_CONNECTOR.getValue()), timeout),
                DMIPortConnectorInformation.class
        );
    }
}
