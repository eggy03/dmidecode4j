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
 * Immutable representation of processor cache information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Cache
 * Information (Type 7) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMICache cache = DMICache.builder()
 *     .socketDesignation("L3-Cache")
 *     .location("Internal")
 *     .installedSize("32 MB")
 *     .associativity("16-way Set-Associative")
 *     .build();
 *
 * // Create a modified copy
 * DMICache updated = cache.toBuilder()
 *     .installedSize("64 MB")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMICache {

    @SerializedName("Socket Designation")
    @Nullable
    String socketDesignation;

    @SerializedName("Configuration")
    @Nullable
    String configuration;

    @SerializedName("Operational Mode")
    @Nullable
    String operationalMode;

    @SerializedName("Location")
    @Nullable
    String location;

    @SerializedName("Installed Size")
    @Nullable
    String installedSize;

    @SerializedName("Maximum Size")
    @Nullable
    String maximumSize;

    @SerializedName("Supported SRAM Types")
    @Nullable
    List<String> supportedSramTypes;

    @SerializedName("Installed SRAM Type")
    @Nullable
    String installedSramType;

    @SerializedName("Speed")
    @Nullable
    String speed;

    @SerializedName("Error Correction Type")
    @Nullable
    String errorCorrectionType;

    @SerializedName("System Type")
    @Nullable
    String systemType;

    @SerializedName("Associativity")
    @Nullable
    String associativity;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
