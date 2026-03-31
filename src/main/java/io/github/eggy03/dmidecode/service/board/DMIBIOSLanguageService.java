/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBIOSLanguage;
import io.github.eggy03.dmidecode.mapper.board.DMIBIOSLanguageMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jspecify.annotations.NonNull;

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
     *         or {@link Optional#empty()} if no BIOS language entry is detected
     *
     * @since 0.1.0
     */
    @Override
    public @NonNull Optional<DMIBIOSLanguage> get(long timeout) {
        return new DMIBIOSLanguageMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BIOS_LANGUAGE.getValue()), timeout),
                DMIBIOSLanguage.class
        );
    }
}