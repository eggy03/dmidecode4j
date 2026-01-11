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
 * Immutable representation of BIOS information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the BIOS
 * (Type 0) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIBIOS bios = DMIBIOS.builder()
 *     .vendor("American Megatrends Inc.")
 *     .version("F10")
 *     .releaseDate("07/15/2023")
 *     .build();
 *
 * // Create a modified copy
 * DMIBIOS updated = bios.toBuilder()
 *     .version("F11")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMIBIOS {

    @SerializedName("Vendor")
    @Nullable
    String vendor;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("Release Date")
    @Nullable
    String releaseDate;

    @SerializedName("Address")
    @Nullable
    String address;

    @SerializedName("Runtime Size")
    @Nullable
    String runtimeSize;

    @SerializedName("ROM Size")
    @Nullable
    String romSize;

    @SerializedName("Characteristics")
    @Nullable
    List<String> characteristics;

    @SerializedName("BIOS Revision")
    @Nullable
    String biosRevision;

    @SerializedName("Firmware Revision")
    @Nullable
    String firmwareRevision;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
