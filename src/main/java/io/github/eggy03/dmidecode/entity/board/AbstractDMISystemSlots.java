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
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMISystemSlots slot = new DMISystemSlots.Builder()
 *     .designation("PCIEX16")
 *     .type("PCI Express")
 *     .currentUsage("In Use")
 *     .busAddress("0000:01:00.0")
 *     .build();
 *
 * // Create a modified copy
 * DMISystemSlots updated = slot
 *     .withCurrentUsage("Available");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableEntityStyle
@NullMarked
public abstract class AbstractDMISystemSlots {

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

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
