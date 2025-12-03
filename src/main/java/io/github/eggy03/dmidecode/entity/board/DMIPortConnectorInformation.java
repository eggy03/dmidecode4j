package io.github.eggy03.dmidecode.entity.board;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

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
