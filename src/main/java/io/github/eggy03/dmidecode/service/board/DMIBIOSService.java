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
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jspecify.annotations.NonNull;

import java.util.List;

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
        return new DMIBIOSMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommandFor(DMIType.BIOS), timeout),
                DMIBIOS.class
        );
    }
}
