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
 * @since 0.2.0
 */
@Value.Immutable
@ImmutableEntityStyle
@NullMarked
@JsonSerialize(as = ImmutableDMIPortConnectorInformation.class)
@JsonDeserialize(as = ImmutableDMIPortConnectorInformation.class)
public abstract class DMIPortConnectorInformation {

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
