package io.github.eggy03.dmidecode.entity.processor;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class DMICache {

    @SerializedName("Socket Designation")
    @Nullable
    String socketDesignation;

    @SerializedName("Configuration")
    @Nullable
    String configuration;

    @SerializedName("Operational Mode")
    @Nullable
    String operationalMode;

    @SerializedName("Location")
    @Nullable
    String location;

    @SerializedName("Installed Size")
    @Nullable
    String installedSize;

    @SerializedName("Maximum Size")
    @Nullable
    String maximumSize;

    @SerializedName("Supported SRAM Types")
    @Nullable
    List<String> supportedSramTypes;

    @SerializedName("Installed SRAM Type")
    @Nullable
    String installedSramType;

    @SerializedName("Speed")
    @Nullable
    String speed;

    @SerializedName("Error Correction Type")
    @Nullable
    String errorCorrectionType;

    @SerializedName("System Type")
    @Nullable
    String systemType;

    @SerializedName("Associativity")
    @Nullable
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
