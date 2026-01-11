/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.processor;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

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
 * DMIProcessor processor = DMIProcessor.builder()
 *     .socketDesignation("CPU0")
 *     .manufacturer("Intel")
 *     .version("Intel(R) Core(TM) i7-12700H")
 *     .coreCount(14)
 *     .threadCount(20)
 *     .currentSpeed("2700 MHz")
 *     .build();
 *
 * // Create a modified copy
 * DMIProcessor updated = processor.toBuilder()
 *     .currentSpeed("3900 MHz")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMIProcessor {

    @SerializedName("Socket Designation")
    @Nullable
    String socketDesignation;

    @SerializedName("Type")
    @Nullable
    String type;

    @SerializedName("Family")
    @Nullable
    String family;

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("ID")
    @Nullable
    String id;

    @SerializedName("Signature")
    @Nullable
    String signature;

    @SerializedName("Flags")
    @Nullable
    List<String> flags;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("Voltage")
    @Nullable
    String voltage;

    @SerializedName("External Clock")
    @Nullable
    String externalClock;

    @SerializedName("Max Speed")
    @Nullable
    String maxSpeed;

    @SerializedName("Current Speed")
    @Nullable
    String currentSpeed;

    @SerializedName("Status")
    @Nullable
    String status;

    @SerializedName("Upgrade")
    @Nullable
    String upgrade;

    @SerializedName("L1 Cache Handle")
    @Nullable
    String l1CacheHandle;

    @SerializedName("L2 Cache Handle")
    @Nullable
    String l2CacheHandle;

    @SerializedName("L3 Cache Handle")
    @Nullable
    String l3CacheHandle;

    @SerializedName("Serial Number")
    @Nullable
    String serialNumber;

    @SerializedName("Asset Tag")
    @Nullable
    String assetTag;

    @SerializedName("Part Number")
    @Nullable
    String partNumber;

    @SerializedName("Core Count")
    @Nullable
    Integer coreCount;

    @SerializedName("Core Enabled")
    @Nullable
    Integer coreEnabled;

    @SerializedName("Thread Count")
    @Nullable
    Integer threadCount;

    @SerializedName("Characteristics")
    @Nullable
    List<String> characteristics;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
