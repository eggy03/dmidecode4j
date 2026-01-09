package io.github.eggy03.dmidecode.mapper;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommonDMIMapperTest {

    @Value
    @Builder(toBuilder = true)
    static class MockEntityClass {

        @Nullable
        @SerializedName("ID")
        Long id;

        @Nullable
        @SerializedName("Value")
        String value;

        @Nullable
        @SerializedName("Values")
        List<String> values;

    }

    private static final StringBuilder singleEntityData = new StringBuilder();
    private static MockEntityClass singleEntityTestClass;

    private static final StringBuilder multipleEntityData = new StringBuilder();
    private static MockEntityClass multipleEntityClassOne;
    private static MockEntityClass multipleEntityClassTwo;

    private static final StringBuilder emptyData = new StringBuilder();

    private final CommonDMIMapper<MockEntityClass> mapper = new CommonDMIMapper<MockEntityClass>() {

    };

    @BeforeAll
    static void setSingleEntityData() {
        
        singleEntityData.append("# dmidecode 3.6\n").
                append("Getting SMBIOS data from sysfs.\n").
                append("SMBIOS 3.3.0 present.\n\n").

                append("Handle 0x0011, DMI type 4, 48 bytes\n").
                append("Processor Information\n").
                append("\tID: 1\n").
                append("\tValue: ValueA\n").
                append("\tValues:\n").
                append("\t\tValue1\n").
                append("\t\tValue2\n");

        singleEntityTestClass = MockEntityClass.builder()
                .id(1L)
                .value("ValueA")
                .values(Arrays.asList("Value1", "Value2"))
                .build();


    }

    @BeforeAll
    static void setEmptyData() {
        emptyData.append("# dmidecode 3.6\n").
                append("Getting SMBIOS data from sysfs.\n").
                append("SMBIOS 3.3.0 present.\n\n");
    }

    @BeforeAll
    static void setMultipleEntityData() {

        multipleEntityData.append("# dmidecode 3.6\n").
                append("Getting SMBIOS data from sysfs.\n").
                append("SMBIOS 3.3.0 present.\n\n").

                append("Handle 0x0011, DMI type 4, 48 bytes\n").
                append("Processor Information\n").
                append("\tID: 1\n").
                append("\tValue: ValueA\n").
                append("\tValues:\n").
                append("\t\tValue1\n").
                append("\t\tValue2\n\n").

                append("Handle 0x0011, DMI type 4, 48 bytes\n").
                append("Processor Information\n").
                append("\tID: 2\n").
                append("\tValue: ValueB\n").
                append("\tValues:\n").
                append("\t\tValue3\n").
                append("\t\tValue4\n\n");

        multipleEntityClassOne = MockEntityClass.builder()
                .id(1L)
                .value("ValueA")
                .values(Arrays.asList("Value1", "Value2"))
                .build();

        multipleEntityClassTwo = MockEntityClass.builder()
                .id(2L)
                .value("ValueB")
                .values(Arrays.asList("Value3", "Value4"))
                .build();


    }

    @Test
    void test_mapToEntity_singleEntity_success() {

        Optional<MockEntityClass> testClass = mapper.mapToEntity(singleEntityData.toString(), MockEntityClass.class);

        assertThat(testClass).isPresent();
        assertThat(testClass).isNotEmpty();
        assertThat(testClass).contains(singleEntityTestClass);
    }

    @Test
    void test_mapToEntity_multipleEntities_success() {
        // should only capture the last entity
        Optional<MockEntityClass> testClass = mapper.mapToEntity(multipleEntityData.toString(), MockEntityClass.class);

        assertThat(testClass).isPresent();
        assertThat(testClass).isNotEmpty();
        assertThat(testClass).contains(multipleEntityClassTwo);
    }

    @Test
    void test_mapToEntity_keyWithNoValue() {
        String data = "ID: 1\n" + "Value:\n" + "Values: ";
        Optional<MockEntityClass> result = mapper.mapToEntity(data, MockEntityClass.class);

        assertThat(result.isPresent());
        assertThat(result).contains(MockEntityClass.builder().id(1L).build());
    }

    @Test
    void test_mapToEntity_empty_success() {
        Optional<MockEntityClass> testClass = mapper.mapToEntity(emptyData.toString(), MockEntityClass.class);
        assertThat(testClass).isPresent();
        assertThat(testClass).contains(MockEntityClass.builder().build()); // empty entity class with null values
    }


    @Test
    void test_mapToEntityList_success() {

        List<MockEntityClass> testClassList = mapper.mapToList(multipleEntityData.toString(), MockEntityClass.class);

        assertThat(testClassList).isNotNull();
        assertThat(testClassList.get(0)).isEqualTo(multipleEntityClassOne);
        assertThat(testClassList.get(1)).isEqualTo(multipleEntityClassTwo);
    }

    @Test
    void test_mapToEntityList_empty_success() {
        List<MockEntityClass> testClassList = mapper.mapToList(emptyData.toString(), MockEntityClass.class);
        assertThat(testClassList).isNotNull();
        assertThat(testClassList.isEmpty());
    }
}