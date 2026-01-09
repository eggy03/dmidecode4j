package io.github.eggy03.dmidecode.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A common mapping interface for converting raw {@code dmidecode} output
 * into strongly typed Java objects.
 * <p>
 * Provides default methods to parse structured DMI text data
 * and map it into either:
 * <ul>
 *     <li>a single {@link Optional} entity</li>
 *     <li>a {@link List} of entities when multiple DMI blocks are present</li>
 * </ul>
 * </p>
 * <p>
 * The mapping process works by:
 * <ol>
 *     <li>Parsing key–value pairs from the raw DMI text</li>
 *     <li>Normalizing single-line and multi-line values</li>
 *     <li>Converting the extracted data into JSON internally</li>
 *     <li>Deserializing the JSON into the requested entity class using Gson</li>
 * </ol>
 * </p>
 *
 * @param <S> the entity type returned by the service implementation
 * @since 0.1.0
 * @author Sayan Bhattacharjee
 */
public interface CommonDMIMapper<S> {

    Gson GSON = new Gson();

    /**
     * Maps raw {@code dmidecode} output into a single entity of type {@code <S>}.
     * <p>
     * If multiple DMI blocks are present in the input, the <strong>last parsed
     * entity</strong> is returned.
     * </p>
     * <p>
     * Multi-line values (for example, lists under keys like {@code Flags:}
     * or {@code Characteristics:}) are automatically aggregated into lists.
     * </p>
     *
     * <p><strong>Example schema:</strong></p>
     * <pre>
     * # dmidecode 3.6
     * Getting SMBIOS data from sysfs.
     * SMBIOS 3.3.0 present.
     *
     * Handle 0x0011, DMI type 4, 48 bytes
     * Processor Information
     *     Socket Designation: FP6
     *     Type: Central Processor
     *     Family: Zen
     *     Flags:
     *         FPU (Floating-point unit on-chip)
     *         VME (Virtual mode extension)
     *     Version: AMD Ryzen 3 5300U with Radeon Graphics
     *     Characteristics:
     *         64-bit capable
     *         Multi-Core
     * </pre>
     * @param rawDMIData the raw {@code dmidecode} output
     * @param mappableEntityClass the target entity class
     * @return an {@link Optional} containing the mapped entity, or empty if
     *         no mappable data is found
     * @since 0.1.0
     */
    default Optional<S> mapToEntity(@Nullable String rawDMIData, @NotNull Class<S> mappableEntityClass) {

        if(rawDMIData==null)
            return Optional.empty();

        Map<String, Object> keyValueMap = new LinkedHashMap<>();

        StringBuilder key = new StringBuilder();
        // for single-line values
        Object value = null;
        // for multi-line values
        List<Object> values = new ArrayList<>();

        // split each line in the active block separated by a newline [each string will be a single line "key:single-line-value" or a multi line "key:multi-line-value"
        for (String currentLine : rawDMIData.split(System.lineSeparator())) {

            if (currentLine.contains(":")) {
                // store the previous key and value if present, indicated by a key of length greater than 0
                if (key.length() > 0) {
                    // if the value has multi lines, insert them or else insert the single line value
                    keyValueMap.put(key.toString(), !values.isEmpty() ? new ArrayList<>(values) : value);
                }
                // Reset state so we can start processing this new "key: value" line
                key.setLength(0);
                value = null;
                values.clear(); // keeping this outside the key length check helps clear out any accumulated DMI headers so that they don't get added in the values. The reason is that these values have no keys, so key length is always 0

                // Split "Key: Value" into LHS and RHS
                String[] parts = currentLine.split(":", 2);
                key.append(parts[0].trim());
                if (!parts[1].trim().isEmpty()) // skip values that are empty near the immediate key, especially in cases where values are multi-lines; for them each value starts in a new line after the key (e.g. "Supported SRAM Types:" before the indented list)
                    value = parts[1].trim();
            } else {// if the value spans across multiple lines, convert the value to an arraylist

                // If we previously captured a single value (for this key),
                // move it into a list so that subsequent values append cleanly.
                if (value != null) { // add the last singular value to the value list. this value is usually the first value in the array of values
                    values.add(value);
                    value = null; // set the value to null the single value does not get repeated during appending the subsequent values
                }
                values.add(currentLine.trim()); // Add the current list item
            }
        }
        // Store the last key/value pair
        if (key.length() > 0) {
            keyValueMap.put(key.toString(), !values.isEmpty() ? new ArrayList<>(values) : value);
        }

        // convert the kv map to JSON and deserialize into an entity class
        JsonElement mapElement = GSON.toJsonTree(keyValueMap);
        return Optional.ofNullable(GSON.fromJson(mapElement, mappableEntityClass));
    }

    /**
     * Maps raw {@code dmidecode} output into a list of entities of type {@code <S>}.
     * <p>
     * Each DMI block separated by a blank line is treated as an independent entity.
     * Empty or non-mappable blocks are ignored.
     * </p>
     * <p>
     * This method is useful for DMI structures that naturally occur multiple times,
     * such as cache information, memory devices, or processor entries.
     * </p>
     *
     * <p><strong>Example schema:</strong></p>
     * <pre>
     * # dmidecode 3.6
     * Getting SMBIOS data from sysfs.
     * SMBIOS 3.3.0 present.
     *
     * Handle 0x000E, DMI type 7, 27 bytes
     * Cache Information
     *     Socket Designation: L1 - Cache
     *     Configuration: Enabled, Not Socketed, Level 1
     *     Supported SRAM Types:
     *         Pipeline Burst
     *     Associativity: 8-way Set-associative
     *
     * Handle 0x000F, DMI type 7, 27 bytes
     * Cache Information
     *     Socket Designation: L2 - Cache
     *     Configuration: Enabled, Not Socketed, Level 2
     *     Supported SRAM Types:
     *         Pipeline Burst
     *     Associativity: 8-way Set-associative
     *
     * Handle 0x0010, DMI type 7, 27 bytes
     * Cache Information
     *     Socket Designation: L3 - Cache
     *     Configuration: Enabled, Not Socketed, Level 3
     *     Supported SRAM Types:
     *         Pipeline Burst
     *     Associativity: 16-way Set-associative
     * </pre>
     *
     * @param rawDMIData the raw {@code dmidecode} output
     * @param mappableEntityClass the target entity class
     * @return a non-null list of mapped entities
     * @since 0.1.0
     */
    default List<S> mapToList(@Nullable String rawDMIData, @NotNull Class<S> mappableEntityClass) {

        if(rawDMIData==null)
            return Collections.emptyList();

        List<S> entityList = new ArrayList<>();

        Map<String, Object> keyValueMap = new HashMap<>();

        StringBuilder key = new StringBuilder();
        // for single-line values
        Object value = null;
        // for multi-line values
        List<Object> values = new ArrayList<>();

        // each DMI block is separated by a double new line
        for (String currentDMIObject : rawDMIData.split(System.lineSeparator() + System.lineSeparator())) {

            // split each line in the active block separated by a newline [each string will be a single line "key:single-line-value" or a multi line "key:multi-line-value"
            for (String currentLine : currentDMIObject.split(System.lineSeparator())) {

                if (currentLine.contains(":")) {
                    // store the previous key and value if present, indicated by a key of length greater than 0
                    if (key.length() > 0) {
                        // if the value has multi lines, insert them or else insert the single line value
                        keyValueMap.put(key.toString(), !values.isEmpty() ? new ArrayList<>(values) : value);
                    }
                    // Reset state so we can start processing this new "key: value" line
                    key.setLength(0);
                    value = null;
                    values.clear(); // keeping this outside the key length check helps clear out any accumulated DMI headers so that they don't get added in the values. The reason is that these values have no keys, so key length is always 0

                    // Split "Key: Value" into LHS and RHS
                    String[] parts = currentLine.split(":", 2);
                    key.append(parts[0].trim());
                    if (!parts[1].trim().isEmpty()) // skip values that are empty near the immediate key, especially in cases where values are multilines; for them each value starts in a new line after the key (e.g. "Supported SRAM Types:" before the indented list)
                        value = parts[1].trim();
                } else {// if the value spans across multiple lines, convert the value to an arraylist

                    // If we previously captured a single value (for this key),
                    // move it into a list so that subsequent values append cleanly.
                    if (value != null) {
                        values.add(value);
                        value = null; // set the value to null the single value does not get repeated during appending the subsequent values
                    }
                    values.add(currentLine.trim()); // Add the current list item
                }
            }
            // Store the last key/value pair in this DMI block
            if (key.length() > 0) {
                keyValueMap.put(key.toString(), !values.isEmpty() ? new ArrayList<>(values) : value);
            }

            // convert each kv map into a JSON and then deserialize into an entity class
            if (!keyValueMap.isEmpty()) { // prevent empty entities from being serialized
                JsonElement mapElement = GSON.toJsonTree(keyValueMap);
                S entity = GSON.fromJson(mapElement, mappableEntityClass);
                if (entity != null)
                    entityList.add(entity);
            }

            // reset the kv map, key and value to get ready for the next DMIObject
            keyValueMap.clear();
            key.setLength(0);
            value = null;
        }
        return entityList;
    }
}