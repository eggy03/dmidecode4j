package io.github.eggy03.dmidecode.entity.processor;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class DMIProcessor {

    @SerializedName("Socket Designation")
    String socketDesignation;

    @SerializedName("Type")
    String type;

    @SerializedName("Family")
    String family;

    @SerializedName("Manufacturer")
    String manufacturer;

    @SerializedName("ID")
    String id;

    @SerializedName("Signature")
    String signature;

    @SerializedName("Flags")
    List<String> flags;

    @SerializedName("Version")
    String version;

    @SerializedName("Voltage")
    String voltage;

    @SerializedName("External Clock")
    String externalClock;

    @SerializedName("Max Speed")
    String maxSpeed;

    @SerializedName("Current Speed")
    String currentSpeed;

    @SerializedName("Status")
    String status;

    @SerializedName("Upgrade")
    String upgrade;

    @SerializedName("L1 Cache Handle")
    String l1CacheHandle;

    @SerializedName("L2 Cache Handle")
    String l2CacheHandle;

    @SerializedName("L3 Cache Handle")
    String l3CacheHandle;

    @SerializedName("Serial Number")
    String serialNumber;

    @SerializedName("Asset Tag")
    String assetTag;

    @SerializedName("Part Number")
    String partNumber;

    @SerializedName("Core Count")
    Integer coreCount;

    @SerializedName("Core Enabled")
    Integer coreEnabled;

    @SerializedName("Thread Count")
    Integer threadCount;

    @SerializedName("Characteristics")
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
