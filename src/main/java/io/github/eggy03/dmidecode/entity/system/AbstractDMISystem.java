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
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMISystem system = new DMISystem.Builder()
 *     .manufacturer("LENOVO")
 *     .productName("ThinkPad T14 Gen 3")
 *     .serialNumber("PF123ABC")
 *     .uuid("4C4C4544-0038-4D10-8051-CAC04F4A1234")
 *     .build();
 *
 * // Create a modified copy
 * DMISystem updated = system
 *     .withSkuNumber("21CFCTO1WW");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableEntityStyle
@NullMarked
public abstract class AbstractDMISystem {
    
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

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
