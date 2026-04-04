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
import tools.jackson.databind.ObjectMapper;

/**
 * Immutable representation of port connector information retrieved via DMI.
 * <p>
 * Fields correspond to properties reported by {@code dmidecode} for the Port
 * Connector Information (Type 8) SMBIOS structure.
 * </p>
 * <p>
 * Instances of this class are thread-safe.
 * </p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * DMIPortConnectorInformation port = new DMIPortConnectorInformation.Builder()
 *     .externalReferenceDesignator("USB1")
 *     .externalConnectorType("USB")
 *     .portType("USB")
 *     .build();
 *
 * // Create a modified copy
 * DMIPortConnectorInformation updated = port
 *     .withPortType("USB Type-C");
 * }</pre>
 *
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableStyle
@NullMarked
public abstract class AbstractDMIPortConnectorInformation {

    @JsonProperty("External Reference Designator")
    @Nullable
    public abstract String externalReferenceDesignator();

    @JsonProperty("Internal Reference Designator")
    @Nullable
    public abstract String internalReferenceDesignator();

    @JsonProperty("External Connector Type")
    @Nullable
    public abstract String externalConnectorType();

    @JsonProperty("Internal Connector Type")
    @Nullable
    public abstract String internalConnectorType();

    @JsonProperty("Port Type")
    @Nullable
    public abstract String portType();

    @Override
    public String toString() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }

}
