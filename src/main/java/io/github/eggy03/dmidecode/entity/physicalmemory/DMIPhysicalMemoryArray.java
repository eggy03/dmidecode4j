package io.github.eggy03.dmidecode.entity.physicalmemory;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder(toBuilder = true)
public class DMIPhysicalMemoryArray {

    @SerializedName("Location")
    @Nullable
    String location;

    @SerializedName("Use")
    @Nullable
    String use;

    @SerializedName("Error Correction Type")
    @Nullable
    String errorCorrectionType;

    @SerializedName("Maximum Capacity")
    @Nullable
    String maximumCapacity;

    @SerializedName("Error Information Handle")
    @Nullable
    String errorInformationHandle;

    @SerializedName("Number Of Devices")
    @Nullable
    Integer numberOfDevices;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
