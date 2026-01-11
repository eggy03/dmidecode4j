/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.peripheral;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

/**
 * Immutable representation of portable battery information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Portable
 * Battery (Type 22) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIPortableBattery battery = DMIPortableBattery.builder()
 *     .location("Internal Battery")
 *     .manufacturer("LG")
 *     .designCapacity("50000 mWh")
 *     .designVoltage("11.4 V")
 *     .build();
 *
 * // Create a modified copy
 * DMIPortableBattery updated = battery.toBuilder()
 *     .maximumError("2%")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMIPortableBattery {

    @SerializedName("Location")
    String location;

    @SerializedName("Manufacturer")
    String manufacturer;

    @SerializedName("Name")
    String name;

    @SerializedName("Design Capacity")
    String designCapacity;

    @SerializedName("Design Voltage")
    String designVoltage;

    @SerializedName("SBDS Version")
    String sbdsVersion;

    @SerializedName("Maximum Error")
    String maximumError;

    @SerializedName("SBDS Serial Number")
    String sbdsSerialNumber;

    @SerializedName("SBDS Manufacture Date")
    String sbdsManufactureDate;

    @SerializedName("SBDS Chemistry")
    String sbdsChemistry;

    @SerializedName("OEM-specific Information")
    String oemSpecificInformation;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
