/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.physicalmemory;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

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
 * DMIPhysicalMemoryArray array = DMIPhysicalMemoryArray.builder()
 *     .location("System Board Or Motherboard")
 *     .use("System Memory")
 *     .maximumCapacity("128 GB")
 *     .numberOfDevices(4)
 *     .build();
 *
 * // Create a modified copy
 * DMIPhysicalMemoryArray updated = array.toBuilder()
 *     .errorCorrectionType("Multi-bit ECC")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMIPhysicalMemoryArray {

    @SerializedName("Location")
    @Nullable
    String location;

    @SerializedName("Use")
    @Nullable
    String use;

    @SerializedName("Error Correction Type")
    @Nullable
    String errorCorrectionType;

    @SerializedName("Maximum Capacity")
    @Nullable
    String maximumCapacity;

    @SerializedName("Error Information Handle")
    @Nullable
    String errorInformationHandle;

    @SerializedName("Number Of Devices")
    @Nullable
    Integer numberOfDevices;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
