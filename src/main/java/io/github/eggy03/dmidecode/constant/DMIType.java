/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.constant;

/**
 * Enumeration of DMI types as defined by the
 * {@code dmidecode} specification.
 * <p>
 * Each enum constant represents a DMI type number that can be
 * queried using the {@code dmidecode --type} command.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * // Build a dmidecode command for querying baseboard information
 * String command = DMIType.getCommandFor(DMIType.BASEBOARD);
 * }</pre>
 *
 * @since 0.1.0
 */
public enum DMIType {

    BIOS(0),
    SYSTEM(1),
    BASEBOARD(2),
    CHASSIS(3),
    PROCESSOR(4),
    MEMORY_CONTROLLER(5),
    MEMORY_MODULE(6),
    CACHE(7),
    PORT_CONNECTOR(8),
    SYSTEM_SLOTS(9),
    ONBOARD_DEVICES(10),
    OEM_SETTINGS(11),
    SYSTEM_CONFIGURATION_OPTIONS(12),
    BIOS_LANGUAGE(13),
    GROUP_ASSOCIATIONS(14),
    SYSTEM_EVENT_LOG(15),
    PHYSICAL_MEMORY_ARRAY(16),
    MEMORY_DEVICE(17),
    THIRTY_TWO_BIT_MEMORY_ERROR(18),
    MEMORY_ARRAY_MAPPED_ADDRESS(19),
    MEMORY_DEVICE_MAPPED_ADDRESS(20),
    BUILT_IN_POINTING_DEVICE(21),
    PORTABLE_BATTERY(22),
    SYSTEM_RESET(23),
    HARDWARE_SECURITY(24),
    SYSTEM_POWER_CONTROLS(25),
    VOLTAGE_PROBE(26),
    COOLING_DEVICE(27),
    TEMPERATURE_PROBE(28),
    ELECTRICAL_CURRENT_PROBE(29),
    OUT_OF_BAND_REMOTE_ACCESS(30),
    BOOT_INTEGRITY_SERVICES(31),
    SYSTEM_BOOT(32),
    SIXTY_FOUR_BIT_MEMORY_ERROR(33),
    MANAGEMENT_DEVICE(34),
    MANAGEMENT_DEVICE_COMPONENT(35),
    MANAGEMENT_DEVICE_THRESHOLD_DATA(36),
    MEMORY_CHANNEL(37),
    IPMI_DEVICE(38),
    POWER_SUPPLY(39),
    ADDITIONAL_INFORMATION(40),
    ONBOARD_DEVICE(41);
    
    private final int value;

    DMIType(int value){this.value = value;}

    public static String getCommandFor(DMIType type){
        return "sudo /usr/sbin/dmidecode --type " + type.value;
    }
}