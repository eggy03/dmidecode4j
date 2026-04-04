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
 * Immutable representation of a baseboard (motherboard) device retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Base Board
 * (Type 2) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIBaseboard board = new DMIBaseboard.Builder()
 *     .manufacturer("ASUSTeK COMPUTER INC.")
 *     .productName("PRIME B550M-A")
 *     .serialNumber("ABC123456")
 *     .build();
 *
 * // Create a modified copy
 * DMIBaseboard updated = board
 *     .withSerialNumber("XYZ987654")
 *     .withProductName("PRIME A320");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableEntityStyle
@NullMarked
public abstract class AbstractDMIBaseboard {

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

    @JsonProperty("Asset Tag")
    @Nullable
    public abstract String assetTag();

    @JsonProperty("Features")
    @Nullable
    public abstract List<@Nullable String> features();

    @JsonProperty("Location In Chassis")
    @Nullable
    public abstract String locationInChassis();

    @JsonProperty("Chassis Handle")
    @Nullable
    public abstract String chassisHandle();

    @JsonProperty("Type")
    @Nullable
    public abstract String type();

    @JsonProperty("Contained Object Handles")
    @Nullable
    public abstract Integer containedObjectHandles();

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
