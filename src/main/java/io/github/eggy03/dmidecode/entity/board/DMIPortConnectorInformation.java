/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.entity.board;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

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
 * DMIPortConnectorInformation port = DMIPortConnectorInformation.builder()
 *     .externalReferenceDesignator("USB1")
 *     .externalConnectorType("USB")
 *     .portType("USB")
 *     .build();
 *
 * // Create a modified copy
 * DMIPortConnectorInformation updated = port.toBuilder()
 *     .portType("USB Type-C")
 *     .build();
 * }</pre>
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 */
@Value
@Builder(toBuilder = true)
public class DMIPortConnectorInformation {

    @SerializedName("External Reference Designator")
    @Nullable
    String externalReferenceDesignator;

    @SerializedName("Internal Reference Designator")
    @Nullable
    String internalReferenceDesignator;

    @SerializedName("External Connector Type")
    @Nullable
    String externalConnectorType;

    @SerializedName("Internal Connector Type")
    @Nullable
    String internalConnectorType;

    @SerializedName("Port Type")
    @Nullable
    String portType;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }

}
