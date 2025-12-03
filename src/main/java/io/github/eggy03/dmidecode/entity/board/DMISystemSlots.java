package io.github.eggy03.dmidecode.entity.board;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class DMISystemSlots {

    @SerializedName("Designation")
    String designation;

    @SerializedName("Type")
    String type;

    @SerializedName("Current Usage")
    String currentUsage;

    @SerializedName("Length")
    String length;

    @SerializedName("ID")
    Integer id;

    @SerializedName("Characteristics")
    List<String> characteristics;

    @SerializedName("Bus Address")
    String busAddress;

    @Override
    public String toString() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }
}
