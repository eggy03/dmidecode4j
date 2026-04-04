/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.memory;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.ObjectMapper;

/**
 * Immutable representation of memory device information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Memory
 * Device (Type 17) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIMemoryDevice memory = new DMIMemoryDevice.Builder()
 *     .locator("DIMM_A1")
 *     .size("16 GB")
 *     .type("DDR4")
 *     .speed("3200 MT/s")
 *     .manufacturer("Samsung")
 *     .build();
 *
 * // Create a modified copy
 * DMIMemoryDevice updated = memory
 *     .withConfiguredMemorySpeed("2933 MT/s");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableStyle
@NullMarked
public abstract class AbstractDMIMemoryDevice {

    @JsonProperty("Array Handle")
    @Nullable
    public abstract String arrayHandle();

    @JsonProperty("Error Information Handle")
    @Nullable
    public abstract String errorInformationHandle();

    @JsonProperty("Total Width")
    @Nullable
    public abstract String totalWidth();

    @JsonProperty("Data Width")
    @Nullable
    public abstract String dataWidth();

    @JsonProperty("Size")
    @Nullable
    public abstract String size();

    @JsonProperty("Form Factor")
    @Nullable
    public abstract String formFactor();

    @JsonProperty("Set")
    @Nullable
    public abstract String set();

    @JsonProperty("Locator")
    @Nullable
    public abstract String locator();

    @JsonProperty("Bank Locator")
    @Nullable
    public abstract String bankLocator();

    @JsonProperty("Type")
    @Nullable
    public abstract String type();

    @JsonProperty("Type Detail")
    @Nullable
    public abstract String typeDetail();

    @JsonProperty("Speed")
    @Nullable
    public abstract String speed();

    @JsonProperty("Manufacturer")
    @Nullable
    public abstract String manufacturer();

    @JsonProperty("Serial Number")
    @Nullable
    public abstract String serialNumber();

    @JsonProperty("Asset Tag")
    @Nullable
    public abstract String assetTag();

    @JsonProperty("Part Number")
    @Nullable
    public abstract String partNumber();

    @JsonProperty("Rank")
    @Nullable
    public abstract Integer rank();

    @JsonProperty("Configured Memory Speed")
    @Nullable
    public abstract String configuredMemorySpeed();

    @JsonProperty("Minimum Voltage")
    @Nullable
    public abstract String minimumVoltage();

    @JsonProperty("Maximum Voltage")
    @Nullable
    public abstract String maximumVoltage();

    @JsonProperty("Configured Voltage")
    @Nullable
    public abstract String configuredVoltage();

    @JsonProperty("Memory Technology")
    @Nullable
    public abstract String memoryTechnology();

    @JsonProperty("Memory Operating Mode Capability")
    @Nullable
    public abstract String memoryOperatingModeCapability();

    @JsonProperty("Firmware Version")
    @Nullable
    public abstract String firmwareVersion();

    @JsonProperty("Module Manufacturer ID")
    @Nullable
    public abstract String moduleManufacturerId();

    @JsonProperty("Module Product ID")
    @Nullable
    public abstract String moduleProductId();

    @JsonProperty("Memory Subsystem Controller Manufacturer ID")
    @Nullable
    public abstract String memorySubsystemControllerManufacturerId();

    @JsonProperty("Memory Subsystem Controller Product ID")
    @Nullable
    public abstract String memorySubsystemControllerProductId();

    @JsonProperty("Non-Volatile Size")
    @Nullable
    public abstract String nonVolatileSize();

    @JsonProperty("Volatile Size")
    @Nullable
    public abstract String volatileSize();

    @JsonProperty("Cache Size")
    @Nullable
    public abstract String cacheSize();

    @JsonProperty("Logical Size")
    @Nullable
    public abstract String logicalSize();

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
