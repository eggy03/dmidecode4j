/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.peripheral;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.peripheral.DMIPortableBattery;
import io.github.eggy03.dmidecode.mapper.peripheral.DMIPortableBatteryMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.List;

/**
 * Service class for fetching portable battery information from the system.
 * <p>
 * This class executes the {@link DMIType#PORTABLE_BATTERY} {@code dmidecode} command
 * and maps the resulting output into {@link DMIPortableBattery} objects.
 * </p>
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * DMIPortableBatteryService service = new DMIPortableBatteryService();
 * List<DMIPortableBattery> batteries = service.get(10);
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
public class DMIPortableBatteryService implements CommonDMIServiceInterface<DMIPortableBattery> {

    /**
     * Retrieves portable battery entries present in the system
     * using an isolated {@code dmidecode} process with a configurable timeout.
     * <p>
     * The process is pre-maturely terminated if execution exceeds the specified timeout.
     * </p>
     *
     * @param timeout the maximum time (in seconds) to wait for the {@code dmidecode}
     *                command to complete before terminating the process
     * @return a list of {@link DMIPortableBattery} objects representing
     *         the system portable battery entries.
     *         Returns an empty list if no portable battery entries are detected.
     *
     * @since 0.1.0
     */
    @Override
    public List<DMIPortableBattery> get(long timeout) {
        return new DMIPortableBatteryMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PORTABLE_BATTERY.getValue()), timeout),
                DMIPortableBattery.class
        );
    }
}
