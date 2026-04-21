/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.annotation.Unmodifiable;
import io.github.eggy03.dmidecode.annotation.fragility.InvokesFragileMethod;
import io.github.eggy03.dmidecode.annotation.fragility.MethodType;
import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIPortConnectorInformation;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;
import io.github.eggy03.dmidecode.mapper.board.DMIPortConnectorInformationMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;

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
 */
public class DMIPortConnectorInformationService implements CommonDMIServiceInterface<DMIPortConnectorInformation> {

    private final TerminalService terminalService;
    private final DMIPortConnectorInformationMapper mapper;

    /**
     * Creates {@link DMIPortConnectorInformationService} with default configuration.
     *
     * @since 0.3.0
     */
    public DMIPortConnectorInformationService() {
        this(new TerminalService(), new DMIPortConnectorInformationMapper());
    }

    /**
     * Package Private constructor with injectable dependencies
     *
     * @param terminalService the {@link TerminalService} instance to use, must not be {@code null}
     * @param mapper          the mapper instance to use, must not be {@code null}
     * @since 0.3.0
     */
    DMIPortConnectorInformationService(@NonNull TerminalService terminalService, @NonNull DMIPortConnectorInformationMapper mapper) {
        this.terminalService = Objects.requireNonNull(terminalService, "terminalService cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

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
     * the system port connector entries.
     * Returns an empty list if no port connector entries are detected.
     * @since 0.1.0
     */
    @Override
    @InvokesFragileMethod(targetClass = CommonDMIMapper.class, methodType = MethodType.INTERFACE_DEFAULT_METHOD)
    public @Unmodifiable @NonNull List<DMIPortConnectorInformation> get(long timeout) {

        TerminalResult result = terminalService.executeCommand(DMIType.PORT_CONNECTOR, timeout);
        return mapper.mapToList(result.getResult(), DMIPortConnectorInformation.class);
    }
}
