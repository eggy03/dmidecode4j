package io.github.eggy03.dmidecode.entity.physicalmemory;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder(toBuilder = true)
public class DMIMemoryDevice {

    @SerializedName("Array Handle")
    @Nullable
    String arrayHandle;

    @SerializedName("Error Information Handle")
    @Nullable
    String errorInformationHandle;

    @SerializedName("Total Width")
    @Nullable
    String totalWidth;

    @SerializedName("Data Width")
    @Nullable
    String dataWidth;

    @SerializedName("Size")
    @Nullable
    String size;

    @SerializedName("Form Factor")
    @Nullable
    String formFactor;

    @SerializedName("Set")
    @Nullable
    String set;

    @SerializedName("Locator")
    @Nullable
    String locator;

    @SerializedName("Bank Locator")
    @Nullable
    String bankLocator;

    @SerializedName("Type")
    @Nullable
    String type;

    @SerializedName("Type Detail")
    @Nullable
    String typeDetail;

    @SerializedName("Speed")
    @Nullable
    String speed;

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("Serial Number")
    @Nullable
    String serialNumber;

    @SerializedName("Asset Tag")
    @Nullable
    String assetTag;

    @SerializedName("Part Number")
    @Nullable
    String partNumber;

    @SerializedName("Rank")
    @Nullable
    Integer rank;

    @SerializedName("Configured Memory Speed")
    @Nullable
    String configuredMemorySpeed;

    @SerializedName("Minimum Voltage")
    @Nullable
    String minimumVoltage;

    @SerializedName("Maximum Voltage")
    @Nullable
    String maximumVoltage;

    @SerializedName("Configured Voltage")
    @Nullable
    String configuredVoltage;

    @SerializedName("Memory Technology")
    @Nullable
    String memoryTechnology;

    @SerializedName("Memory Operating Mode Capability")
    @Nullable
    String memoryOperatingModeCapability;

    @SerializedName("Firmware Version")
    @Nullable
    String firmwareVersion;

    @SerializedName("Module Manufacturer ID")
    @Nullable
    String moduleManufacturerId;

    @SerializedName("Module Product ID")
    @Nullable
    String moduleProductId;

    @SerializedName("Memory Subsystem Controller Manufacturer ID")
    @Nullable
    String memorySubsystemControllerManufacturerId;

    @SerializedName("Memory Subsystem Controller Product ID")
    @Nullable
    String memorySubsystemControllerProductId;

    @SerializedName("Non-Volatile Size")
    @Nullable
    String nonVolatileSize;

    @SerializedName("Volatile Size")
    @Nullable
    String volatileSize;

    @SerializedName("Cache Size")
    @Nullable
    String cacheSize;

    @SerializedName("Logical Size")
    @Nullable
    String logicalSize;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
