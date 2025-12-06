package io.github.eggy03.dmidecode.entity.peripheral;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

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
