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
import io.github.eggy03.dmidecode.entity.board.DMIBIOS;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;
import io.github.eggy03.dmidecode.mapper.board.DMIBIOSMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;

/**
 * Service class for fetching BIOS information from the system.
 * <p>
 * This class executes the {@link DMIType#BIOS} {@code dmidecode} command
 * and maps the resulting output into {@link DMIBIOS} objects.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIBIOSService service = new DMIBIOSService();
 * List<DMIBIOS> biosList = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 */
public class DMIBIOSService implements CommonDMIServiceInterface<DMIBIOS> {

    private final TerminalService terminalService;
    private final DMIBIOSMapper mapper;

    /**
     * Creates {@link DMIBIOSService} with default configuration.
     *
     * @since 0.3.0
     */
    public DMIBIOSService() {
        this(new TerminalService(), new DMIBIOSMapper());
    }

    /**
     * Package Private constructor with injectable dependencies
     *
     * @param terminalService the {@link TerminalService} instance to use, must not be {@code null}
     * @param mapper          the mapper instance to use, must not be {@code null}
     * @since 0.3.0
     */
    DMIBIOSService(@NonNull TerminalService terminalService, @NonNull DMIBIOSMapper mapper) {
        this.terminalService = Objects.requireNonNull(terminalService, "terminalService cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

    /**
     * Retrieves BIOS entries present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return a list of {@link DMIBIOS} objects representing the system BIOS entries.
     * Returns an empty list if no BIOS entries are detected.
     * @since 0.1.0
     */
    @Override
    @InvokesFragileMethod(targetClass = CommonDMIMapper.class, methodType = MethodType.INTERFACE_DEFAULT_METHOD)
    public @NonNull @Unmodifiable List<DMIBIOS> get(long timeout) {

        TerminalResult result = terminalService.executeCommand(DMIType.BIOS, timeout);
        return mapper.mapToList(result.getResult(), DMIBIOS.class);
    }
}
