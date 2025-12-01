package io.github.eggy03.dmidecode.entity.board;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
