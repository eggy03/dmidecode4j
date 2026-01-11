/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.physicalmemory;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.physicalmemory.DMIPhysicalMemoryArray;
import io.github.eggy03.dmidecode.mapper.physicalmemory.DMIPhysicalMemoryArrayMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.Optional;

/**
 * Service class for fetching physical memory array information from the system.
 * <p>
 * This class executes the {@link DMIType#PHYSICAL_MEMORY_ARRAY} {@code dmidecode} command
 * and maps the resulting output into a {@link DMIPhysicalMemoryArray} object.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIPhysicalMemoryArrayService service = new DMIPhysicalMemoryArrayService();
 * Optional<DMIPhysicalMemoryArray> memoryArray = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
public class DMIPhysicalMemoryArrayService implements OptionalCommonDMIServiceInterface<DMIPhysicalMemoryArray> {

    /**
     * Retrieves physical memory array information present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return an {@link Optional} containing {@link DMIPhysicalMemoryArray} information
     *         if present, or {@link Optional#empty()} if no physical memory array
     *         entry is detected
     *
     * @since 0.1.0
     */
    @Override
    public Optional<DMIPhysicalMemoryArray> get(long timeout) {
        return new DMIPhysicalMemoryArrayMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PHYSICAL_MEMORY_ARRAY.getValue()), timeout),
                DMIPhysicalMemoryArray.class
        );
    }
}
