/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.memory;

import io.github.eggy03.dmidecode.annotation.Unmodifiable;
import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.memory.DMIMemoryDevice;
import io.github.eggy03.dmidecode.mapper.physicalmemory.DMIMemoryDeviceMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jspecify.annotations.NonNull;

import java.util.List;

/**
 * Service class for fetching memory device information from the system.
 * <p>
 * This class executes the {@link DMIType#MEMORY_DEVICE} {@code dmidecode} command
 * and maps the resulting output into {@link DMIMemoryDevice} objects.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIMemoryDeviceService service = new DMIMemoryDeviceService();
 * List<DMIMemoryDevice> memoryDevices = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
public class DMIMemoryDeviceService implements CommonDMIServiceInterface<DMIMemoryDevice> {

    /**
     * Retrieves memory device entries present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return a list of {@link DMIMemoryDevice} objects representing
     *         the system memory device entries.
     *         Returns an empty list if no memory device entries are detected.
     *
     * @since 0.1.0
     */
    @Override
    public @Unmodifiable @NonNull List<DMIMemoryDevice> get(long timeout) {
        return new DMIMemoryDeviceMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.MEMORY_DEVICE.getValue()), timeout),
                DMIMemoryDevice.class
        );
    }
}
