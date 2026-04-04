/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.processor;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Immutable representation of processor information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Processor
 * Information (Type 4) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIProcessor processor = new DMIProcessor.Builder()
 *     .socketDesignation("CPU0")
 *     .manufacturer("Intel")
 *     .version("Intel(R) Core(TM) i7-12700H")
 *     .coreCount(14)
 *     .threadCount(20)
 *     .currentSpeed("2700 MHz")
 *     .build();
 *
 * // Create a modified copy
 * DMIProcessor updated = processor
 *     .withCurrentSpeed("3900 MHz");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableStyle
@NullMarked
public abstract class AbstractDMIProcessor {

    @JsonProperty("Socket Designation")
    @Nullable
    public abstract String socketDesignation();

    @JsonProperty("Type")
    @Nullable
    public abstract String type();

    @JsonProperty("Family")
    @Nullable
    public abstract String family();

    @JsonProperty("Manufacturer")
    @Nullable
    public abstract String manufacturer();

    @JsonProperty("ID")
    @Nullable
    public abstract String id();

    @JsonProperty("Signature")
    @Nullable
    public abstract String signature();

    @JsonProperty("Flags")
    @Nullable
    public abstract List<@Nullable String> flags();

    @JsonProperty("Version")
    @Nullable
    public abstract String version();

    @JsonProperty("Voltage")
    @Nullable
    public abstract String voltage();

    @JsonProperty("External Clock")
    @Nullable
    public abstract String externalClock();

    @JsonProperty("Max Speed")
    @Nullable
    public abstract String maxSpeed();

    @JsonProperty("Current Speed")
    @Nullable
    public abstract String currentSpeed();

    @JsonProperty("Status")
    @Nullable
    public abstract String status();

    @JsonProperty("Upgrade")
    @Nullable
    public abstract String upgrade();

    @JsonProperty("L1 Cache Handle")
    @Nullable
    public abstract String l1CacheHandle();

    @JsonProperty("L2 Cache Handle")
    @Nullable
    public abstract String l2CacheHandle();

    @JsonProperty("L3 Cache Handle")
    @Nullable
    public abstract String l3CacheHandle();

    @JsonProperty("Serial Number")
    @Nullable
    public abstract String serialNumber();

    @JsonProperty("Asset Tag")
    @Nullable
    public abstract String assetTag();

    @JsonProperty("Part Number")
    @Nullable
    public abstract String partNumber();

    @JsonProperty("Core Count")
    @Nullable
    public abstract Integer coreCount();

    @JsonProperty("Core Enabled")
    @Nullable
    public abstract Integer coreEnabled();

    @JsonProperty("Thread Count")
    @Nullable
    public abstract Integer threadCount();

    @JsonProperty("Characteristics")
    @Nullable
    public abstract List<@Nullable String> characteristics();

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
