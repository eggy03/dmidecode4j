/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service.peripheral;

import io.github.eggy03.dmidecode.annotation.Unmodifiable;
import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.peripheral.DMIPortableBattery;
import io.github.eggy03.dmidecode.mapper.peripheral.DMIPortableBatteryMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.terminal.TerminalResult;
import io.github.eggy03.dmidecode.terminal.TerminalService;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;

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
 */
public class DMIPortableBatteryService implements CommonDMIServiceInterface<DMIPortableBattery> {

    private final TerminalService terminalService;
    private final DMIPortableBatteryMapper mapper;

    /**
     * Creates {@link DMIPortableBatteryService} with default configuration.
     *
     * @since 0.3.0
     */
    public DMIPortableBatteryService() {
        this(new TerminalService(), new DMIPortableBatteryMapper());
    }

    /**
     * Package Private constructor with injectable dependencies
     *
     * @param terminalService the {@link TerminalService} instance to use, must not be {@code null}
     * @param mapper          the mapper instance to use, must not be {@code null}
     * @since 0.3.0
     */
    DMIPortableBatteryService(@NonNull TerminalService terminalService, @NonNull DMIPortableBatteryMapper mapper) {
        this.terminalService = Objects.requireNonNull(terminalService, "terminalService cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

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
     * the system portable battery entries.
     * Returns an empty list if no portable battery entries are detected.
     * @since 0.1.0
     */
    @Override
    public @Unmodifiable @NonNull List<DMIPortableBattery> get(long timeout) {

        TerminalResult result = terminalService.executeCommand(DMIType.PORTABLE_BATTERY, timeout);
        return mapper.mapToList(result.getResult(), DMIPortableBattery.class);
    }
}
