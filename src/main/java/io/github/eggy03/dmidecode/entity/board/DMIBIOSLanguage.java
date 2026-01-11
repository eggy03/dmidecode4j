/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.board;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Immutable representation of BIOS language information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the BIOS
 * Language (Type 13) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIBIOSLanguage language = DMIBIOSLanguage.builder()
 *     .installableLanguages(List.of("en|US", "fr|FR"))
 *     .currentLanguage("en|US")
 *     .build();
 *
 * // Create a modified copy
 * DMIBIOSLanguage updated = language.toBuilder()
 *     .currentLanguage("fr|FR")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
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
