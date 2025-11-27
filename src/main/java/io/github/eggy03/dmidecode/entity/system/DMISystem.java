package io.github.eggy03.dmidecode.entity.system;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

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
