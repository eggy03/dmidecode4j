/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableEntityStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

/**
 * Immutable representation of portable battery information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Portable
 * Battery (Type 22) SMBIOS structure.
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
@JsonSerialize(as = ImmutableDMIPortableBattery.class)
@JsonDeserialize(as = ImmutableDMIPortableBattery.class)
public abstract class DMIPortableBattery {

    @JsonProperty("Location")
    @Nullable
    public abstract String location();

    @JsonProperty("Manufacturer")
    @Nullable
    public abstract String manufacturer();

    @JsonProperty("Name")
    @Nullable
    public abstract String name();

    @JsonProperty("Design Capacity")
    @Nullable
    public abstract String designCapacity();

    @JsonProperty("Design Voltage")
    @Nullable
    public abstract String designVoltage();

    @JsonProperty("SBDS Version")
    @Nullable
    public abstract String sbdsVersion();

    @JsonProperty("Maximum Error")
    @Nullable
    public abstract String maximumError();

    @JsonProperty("SBDS Serial Number")
    @Nullable
    public abstract String sbdsSerialNumber();

    @JsonProperty("SBDS Manufacture Date")
    @Nullable
    public abstract String sbdsManufactureDate();

    @JsonProperty("SBDS Chemistry")
    @Nullable
    public abstract String sbdsChemistry();

    @JsonProperty("OEM-specific Information")
    @Nullable
    public abstract String oemSpecificInformation();

    public String toJson() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
