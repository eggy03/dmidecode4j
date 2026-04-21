/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableEntityStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

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
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableEntityStyle
@NullMarked
@JsonSerialize(as = ImmutableDMIBIOSLanguage.class)
@JsonDeserialize(as = ImmutableDMIBIOSLanguage.class)
public abstract class DMIBIOSLanguage {

    @JsonProperty("Installable Languages")
    @Nullable
    public abstract List<@Nullable String> installableLanguages();

    @JsonProperty("Currently Installed Language")
    @Nullable
    public abstract String currentLanguage();

    public String toJson() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
