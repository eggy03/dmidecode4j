/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.memory;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableEntityStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

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
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableEntityStyle
@NullMarked
@JsonSerialize(as = ImmutableDMIPhysicalMemoryArray.class)
@JsonDeserialize(as = ImmutableDMIPhysicalMemoryArray.class)
public abstract class DMIPhysicalMemoryArray {

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

    public String toJson() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
