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

import java.util.List;

/**
 * Immutable representation of a baseboard (motherboard) device retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Base Board
 * (Type 2) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIBaseboard board = DMIBaseboard.builder()
 *     .manufacturer("ASUSTeK COMPUTER INC.")
 *     .productName("PRIME B550M-A")
 *     .serialNumber("ABC123456")
 *     .build();
 *
 * // Create a modified copy
 * DMIBaseboard updated = board.toBuilder()
 *     .serialNumber("XYZ987654")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMIBaseboard {

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("Product Name")
    @Nullable
    String productName;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("Serial Number")
    @Nullable
    String serialNumber;

    @SerializedName("Asset Tag")
    @Nullable
    String assetTag;

    @SerializedName("Features")
    @Nullable
    List<String> features;

    @SerializedName("Location In Chassis")
    @Nullable
    String locationInChassis;

    @SerializedName("Chassis Handle")
    @Nullable
    String chassisHandle;

    @SerializedName("Type")
    @Nullable
    String type;

    @SerializedName("Contained Object Handles")
    @Nullable
    Integer containedObjectHandles;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
