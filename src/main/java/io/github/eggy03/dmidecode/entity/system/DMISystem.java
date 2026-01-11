/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.system;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of system information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the System
 * Information (Type 1) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMISystem system = DMISystem.builder()
 *     .manufacturer("LENOVO")
 *     .productName("ThinkPad T14 Gen 3")
 *     .serialNumber("PF123ABC")
 *     .uuid("4C4C4544-0038-4D10-8051-CAC04F4A1234")
 *     .build();
 *
 * // Create a modified copy
 * DMISystem updated = system.toBuilder()
 *     .skuNumber("21CFCTO1WW")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMISystem {

    @Nullable
    @SerializedName("Manufacturer")
    String manufacturer;

    @Nullable
    @SerializedName("Product Name")
    String productName;

    @Nullable
    @SerializedName("Version")
    String version;

    @Nullable
    @SerializedName("Serial Number")
    String serialNumber;

    @Nullable
    @SerializedName("UUID")
    String uuid;

    @Nullable
    @SerializedName("Wake-up Type")
    String wakeupType;

    @Nullable
    @SerializedName("SKU Number")
    String skuNumber;

    @Nullable
    @SerializedName("Family")
    String family;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
