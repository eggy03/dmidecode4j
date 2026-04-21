/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.annotation.fragility.InvokesFragileMethod;
import io.github.eggy03.dmidecode.annotation.fragility.MethodType;
import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBIOSLanguage;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;
import io.github.eggy03.dmidecode.mapper.board.DMIBIOSLanguageMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Service class for fetching BIOS language information from the system.
 * <p>
 * This class executes the {@link DMIType#BIOS_LANGUAGE} {@code dmidecode} command
 * and maps the resulting output into a {@link DMIBIOSLanguage} object.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIBIOSLanguageService service = new DMIBIOSLanguageService();
 * Optional<DMIBIOSLanguage> biosLanguage = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 */
public class DMIBIOSLanguageService implements OptionalCommonDMIServiceInterface<DMIBIOSLanguage> {

    private final TerminalService terminalService;
    private final DMIBIOSLanguageMapper mapper;

    /**
     * Creates {@link DMIBIOSLanguageService} with default configuration.
     *
     * @since 0.3.0
     */
    public DMIBIOSLanguageService() {
        this(new TerminalService(), new DMIBIOSLanguageMapper());
    }

    /**
     * Package Private constructor with injectable dependencies
     *
     * @param terminalService the {@link TerminalService} instance to use, must not be {@code null}
     * @param mapper          the mapper instance to use, must not be {@code null}
     * @since 0.3.0
     */
    DMIBIOSLanguageService(@NonNull TerminalService terminalService, @NonNull DMIBIOSLanguageMapper mapper) {
        this.terminalService = Objects.requireNonNull(terminalService, "terminalService cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

    /**
     * Retrieves BIOS language information present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return an {@link Optional} containing {@link DMIBIOSLanguage} information if present,
     * or {@link Optional#empty()} if no BIOS language entry is detected
     * @since 0.1.0
     */
    @Override
    @InvokesFragileMethod(targetClass = CommonDMIMapper.class, methodType = MethodType.INTERFACE_DEFAULT_METHOD)
    public @NonNull Optional<DMIBIOSLanguage> get(long timeout) {

        TerminalResult result = terminalService.executeCommand(DMIType.BIOS_LANGUAGE, timeout);
        return mapper.mapToEntity(result.getResult(), DMIBIOSLanguage.class);
    }
}