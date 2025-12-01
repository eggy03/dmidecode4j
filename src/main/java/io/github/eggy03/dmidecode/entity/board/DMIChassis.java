package io.github.eggy03.dmidecode.entity.board;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

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
