package io.github.eggy03.dmidecode.entity.cache;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class DMICache {

    @SerializedName("Socket Designation")
    String socketDesignation;

    @SerializedName("Configuration")
    String configuration;

    @SerializedName("Operational Mode")
    String operationalMode;

    @SerializedName("Location")
    String location;

    @SerializedName("Installed Size")
    String installedSize;

    @SerializedName("Maximum Size")
    String maximumSize;

    @SerializedName("Supported SRAM Types")
    List<String> supportedSramTypes;

    @SerializedName("Installed SRAM Type")
    String installedSramType;

    @SerializedName("Speed")
    String speed;

    @SerializedName("Error Correction Type")
    String errorCorrectionType;

    @SerializedName("System Type")
    String systemType;

    @SerializedName("Associativity")
    String associativity;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
