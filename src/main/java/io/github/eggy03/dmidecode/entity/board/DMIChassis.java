/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableEntityStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

/**
 * Immutable representation of system chassis information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Chassis
 * (Type 3) SMBIOS structure.
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
@JsonSerialize(as = ImmutableDMIChassis.class)
@JsonDeserialize(as = ImmutableDMIChassis.class)
public abstract class DMIChassis {

    @JsonProperty("Manufacturer")
    @Nullable
    public abstract String manufacturer();

    @JsonProperty("Type")
    @Nullable
    public abstract String type();

    @JsonProperty("Lock")
    @Nullable
    public abstract String lock();

    @JsonProperty("Version")
    @Nullable
    public abstract String version();

    @JsonProperty("Serial Number")
    @Nullable
    public abstract String serialNumber();

    @JsonProperty("Asset Tag")
    @Nullable
    public abstract String assetTag();

    @JsonProperty("Boot-up State")
    @Nullable
    public abstract String bootUpState();

    @JsonProperty("Power Supply State")
    @Nullable
    public abstract String powerSupplyState();

    @JsonProperty("Thermal State")
    @Nullable
    public abstract String thermalState();

    @JsonProperty("Security Status")
    @Nullable
    public abstract String securityStatus();

    @JsonProperty("OEM Information")
    @Nullable
    public abstract String oemInformation();

    @JsonProperty("Height")
    @Nullable
    public abstract String height();

    @JsonProperty("Number Of Power Cords")
    @Nullable
    public abstract Integer numberOfPowerCords();

    @JsonProperty("Contained Elements")
    @Nullable
    public abstract Integer containedElements();

    @JsonProperty("SKU Number")
    @Nullable
    public abstract String skuNumber();

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }

}
