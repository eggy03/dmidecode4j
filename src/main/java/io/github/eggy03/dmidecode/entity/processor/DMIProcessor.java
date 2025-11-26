package io.github.eggy03.dmidecode.entity.processor;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class DMIProcessor {

    @SerializedName("Socket Designation")
    @Nullable
    String socketDesignation;

    @SerializedName("Type")
    @Nullable
    String type;

    @SerializedName("Family")
    @Nullable
    String family;

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("ID")
    @Nullable
    String id;

    @SerializedName("Signature")
    @Nullable
    String signature;

    @SerializedName("Flags")
    @Nullable
    List<String> flags;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("Voltage")
    @Nullable
    String voltage;

    @SerializedName("External Clock")
    @Nullable
    String externalClock;

    @SerializedName("Max Speed")
    @Nullable
    String maxSpeed;

    @SerializedName("Current Speed")
    @Nullable
    String currentSpeed;

    @SerializedName("Status")
    @Nullable
    String status;

    @SerializedName("Upgrade")
    @Nullable
    String upgrade;

    @SerializedName("L1 Cache Handle")
    @Nullable
    String l1CacheHandle;

    @SerializedName("L2 Cache Handle")
    @Nullable
    String l2CacheHandle;

    @SerializedName("L3 Cache Handle")
    @Nullable
    String l3CacheHandle;

    @SerializedName("Serial Number")
    @Nullable
    String serialNumber;

    @SerializedName("Asset Tag")
    @Nullable
    String assetTag;

    @SerializedName("Part Number")
    @Nullable
    String partNumber;

    @SerializedName("Core Count")
    @Nullable
    Integer coreCount;

    @SerializedName("Core Enabled")
    @Nullable
    Integer coreEnabled;

    @SerializedName("Thread Count")
    @Nullable
    Integer threadCount;

    @SerializedName("Characteristics")
    @Nullable
    List<String> characteristics;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
