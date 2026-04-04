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
 * Immutable representation of physical memory array information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Physical
 * Memory Array (Type 16) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIPhysicalMemoryArray array = new DMIPhysicalMemoryArray.Builder()
 *     .location("System Board Or Motherboard")
 *     .use("System Memory")
 *     .maximumCapacity("128 GB")
 *     .numberOfDevices(4)
 *     .build();
 *
 * // Create a modified copy
 * DMIPhysicalMemoryArray updated = array
 *     .withErrorCorrectionType("Multi-bit ECC");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableStyle
@NullMarked
public abstract class AbstractDMIPhysicalMemoryArray {

    @JsonProperty("Location")
    @Nullable
    public abstract String location();

    @JsonProperty("Use")
    @Nullable
    public abstract String use();

    @JsonProperty("Error Correction Type")
    @Nullable
    public abstract String errorCorrectionType();

    @JsonProperty("Maximum Capacity")
    @Nullable
    public abstract String maximumCapacity();

    @JsonProperty("Error Information Handle")
    @Nullable
    public abstract String errorInformationHandle();

    @JsonProperty("Number Of Devices")
    @Nullable
    public abstract Integer numberOfDevices();

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
