/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.eggy03.dmidecode.annotation.ImmutableEntityStyle;
import org.immutables.value.Value;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

/**
 * Immutable representation of system information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the System
 * Information (Type 1) SMBIOS structure.
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
@JsonSerialize(as = ImmutableDMISystem.class)
@JsonDeserialize(as = ImmutableDMISystem.class)
public abstract class DMISystem {

    @JsonProperty("Manufacturer")
    @Nullable
    public abstract String manufacturer();

    @JsonProperty("Product Name")
    @Nullable
    public abstract String productName();

    @JsonProperty("Version")
    @Nullable
    public abstract String version();

    @JsonProperty("Serial Number")
    @Nullable
    public abstract String serialNumber();

    @JsonProperty("UUID")
    @Nullable
    public abstract String uuid();

    @JsonProperty("Wake-up Type")
    @Nullable
    public abstract String wakeupType();

    @JsonProperty("SKU Number")
    @Nullable
    public abstract String skuNumber();

    @JsonProperty("Family")
    @Nullable
    public abstract String family();

    public String toJson() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
