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

import java.util.List;

/**
 * Immutable representation of system slot information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the System
 * Slots (Type 9) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMISystemSlots slot = DMISystemSlots.builder()
 *     .designation("PCIEX16")
 *     .type("PCI Express")
 *     .currentUsage("In Use")
 *     .busAddress("0000:01:00.0")
 *     .build();
 *
 * // Create a modified copy
 * DMISystemSlots updated = slot.toBuilder()
 *     .currentUsage("Available")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMISystemSlots {

    @SerializedName("Designation")
    String designation;

    @SerializedName("Type")
    String type;

    @SerializedName("Current Usage")
    String currentUsage;

    @SerializedName("Length")
    String length;

    @SerializedName("ID")
    Integer id;

    @SerializedName("Characteristics")
    List<String> characteristics;

    @SerializedName("Bus Address")
    String busAddress;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
