/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.processor;

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
 * Immutable representation of processor cache information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Cache
 * Information (Type 7) SMBIOS structure.
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
@JsonSerialize(as = ImmutableDMICache.class)
@JsonDeserialize(as = ImmutableDMICache.class)
public abstract class DMICache {

    @JsonProperty("Socket Designation")
    @Nullable
    public abstract String socketDesignation();

    @JsonProperty("Configuration")
    @Nullable
    public abstract String configuration();

    @JsonProperty("Operational Mode")
    @Nullable
    public abstract String operationalMode();

    @JsonProperty("Location")
    @Nullable
    public abstract String location();

    @JsonProperty("Installed Size")
    @Nullable
    public abstract String installedSize();

    @JsonProperty("Maximum Size")
    @Nullable
    public abstract String maximumSize();

    @JsonProperty("Supported SRAM Types")
    @Nullable
    public abstract List<@Nullable String> supportedSramTypes();

    @JsonProperty("Installed SRAM Type")
    @Nullable
    public abstract String installedSramType();

    @JsonProperty("Speed")
    @Nullable
    public abstract String speed();

    @JsonProperty("Error Correction Type")
    @Nullable
    public abstract String errorCorrectionType();

    @JsonProperty("System Type")
    @Nullable
    public abstract String systemType();

    @JsonProperty("Associativity")
    @Nullable
    public abstract String associativity();

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
