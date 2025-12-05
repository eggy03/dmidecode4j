package io.github.eggy03.dmidecode.entity.board;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class DMIBIOS {

    @SerializedName("Vendor")
    @Nullable
    String vendor;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("Release Date")
    @Nullable
    String releaseDate;

    @SerializedName("Address")
    @Nullable
    String address;

    @SerializedName("Runtime Size")
    @Nullable
    String runtimeSize;

    @SerializedName("ROM Size")
    @Nullable
    String romSize;

    @SerializedName("Characteristics")
    @Nullable
    List<String> characteristics;

    @SerializedName("BIOS Revision")
    @Nullable
    String biosRevision;

    @SerializedName("Firmware Revision")
    @Nullable
    String firmwareRevision;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
