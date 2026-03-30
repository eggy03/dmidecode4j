/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

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
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIChassis chassis = new DMIChassis.Builder()
 *     .manufacturer("Dell Inc.")
 *     .type("Desktop")
 *     .serialNumber("ABC123456")
 *     .build();
 *
 * // Create a modified copy
 * DMIChassis updated = chassis
 *     .withAssetTag("OFFICE-PC-01");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableStyle
@NullMarked
public abstract class AbstractDMIChassis {

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

}
