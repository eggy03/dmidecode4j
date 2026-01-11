/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.board;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

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
 * DMIChassis chassis = DMIChassis.builder()
 *     .manufacturer("Dell Inc.")
 *     .type("Desktop")
 *     .serialNumber("ABC123456")
 *     .build();
 *
 * // Create a modified copy
 * DMIChassis updated = chassis.toBuilder()
 *     .assetTag("OFFICE-PC-01")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMIChassis {

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("Type")
    @Nullable
    String type;

    @SerializedName("Lock")
    @Nullable
    String lock;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("Serial Number")
    @Nullable
    String serialNumber;

    @SerializedName("Asset Tag")
    @Nullable
    String assetTag;

    @SerializedName("Boot-up State")
    @Nullable
    String bootUpState;

    @SerializedName("Power Supply State")
    @Nullable
    String powerSupplyState;

    @SerializedName("Thermal State")
    @Nullable
    String thermalState;

    @SerializedName("Security Status")
    @Nullable
    String securityStatus;

    @SerializedName("OEM Information")
    @Nullable
    String oemInformation;

    @SerializedName("Height")
    @Nullable
    String height;

    @SerializedName("Number Of Power Cords")
    @Nullable
    Integer numberOfPowerCords;

    @SerializedName("Contained Elements")
    @Nullable
    Integer containedElements;

    @SerializedName("SKU Number")
    @Nullable
    String skuNumber;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
