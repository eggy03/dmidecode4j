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
 * Immutable representation of system slot information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the System
 * Slots (Type 9) SMBIOS structure.
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
@JsonSerialize(as = ImmutableDMISystemSlots.class)
@JsonDeserialize(as = ImmutableDMISystemSlots.class)
public abstract class DMISystemSlots {

    @JsonProperty("Designation")
    @Nullable
    public abstract String designation();

    @JsonProperty("Type")
    @Nullable
    public abstract String type();

    @JsonProperty("Current Usage")
    @Nullable
    public abstract String currentUsage();

    @JsonProperty("Length")
    @Nullable
    public abstract String length();

    @JsonProperty("ID")
    @Nullable
    public abstract Integer id();

    @JsonProperty("Characteristics")
    @Nullable
    public abstract List<@Nullable String> characteristics();

    @JsonProperty("Bus Address")
    @Nullable
    public abstract String busAddress();

    public String toJson() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
