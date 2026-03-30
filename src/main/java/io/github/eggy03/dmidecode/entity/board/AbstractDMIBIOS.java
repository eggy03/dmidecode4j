/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Immutable representation of BIOS information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the BIOS
 * (Type 0) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIBIOS bios = new DMIBIOS.Builder()
 *     .vendor("American Megatrends Inc.")
 *     .version("F10")
 *     .releaseDate("07/15/2023")
 *     .build();
 *
 * // Create a modified copy
 * DMIBIOS updated = bios
 *     .withVersion("F11");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableStyle
@NullMarked
public abstract class AbstractDMIBIOS {

    @JsonProperty("Vendor")
    @Nullable
    public abstract String vendor();

    @JsonProperty("Version")
    @Nullable
    public abstract String version();

    @JsonProperty("Release Date")
    @Nullable
    public abstract String releaseDate();

    @JsonProperty("Address")
    @Nullable
    public abstract String address();

    @JsonProperty("Runtime Size")
    @Nullable
    public abstract String runtimeSize();

    @JsonProperty("ROM Size")
    @Nullable
    public abstract String romSize();

    @JsonProperty("Characteristics")
    @Nullable
    public abstract List<@Nullable String> characteristics();

    @JsonProperty("BIOS Revision")
    @Nullable
    public abstract String biosRevision();

    @JsonProperty("Firmware Revision")
    @Nullable
    public abstract String firmwareRevision();
}
