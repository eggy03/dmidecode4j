package io.github.eggy03.dmidecode.entity.bios;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class DMIBIOSLanguage {

    @SerializedName("Installable Languages")
    @Nullable
    List<String> installableLanguages;

    @SerializedName("Currently Installed Language")
    @Nullable
    String currentLanguage;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
