package io.github.eggy03.dmidecode.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommonDMIMapperTest {

    private static final String LS = System.lineSeparator();

    static class MockEntityClass {

        @Nullable
        @JsonProperty("ID")
        Long id;

        @Nullable
        @JsonProperty("Value")
        String value;

        @Nullable
        @JsonProperty("Values")
        List<@Nullable String> values;

        public MockEntityClass() {
            this.id=null;
            this.value=null;
            this.values=null;
        }

        public MockEntityClass(@Nullable Long id, @Nullable String value, @Nullable List<@Nullable String> values) {
            this.id=id;
            this.value=value;
            this.values=values;
        }

        @Override
        public String toString() {
            return "{\n\t" + this.id + ",\n\t" + this.value + ",\n\t" + this.values + "\n}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MockEntityClass)) return false;
            MockEntityClass that = (MockEntityClass) o;
            return Objects.equals(id, that.id) &&
                    Objects.equals(value, that.value) &&
                    Objects.equals(values, that.values);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, value, values);
        }

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

        singleEntityData
                .append("# dmidecode 3.6").append(LS)
                .append("Getting SMBIOS data from sysfs.").append(LS)
                .append("SMBIOS 3.3.0 present.").append(LS).append(LS)

                .append("Handle 0x0011, DMI type 4, 48 bytes").append(LS)
                .append("Processor Information").append(LS)
                .append("\tID: 1").append(LS)
                .append("\tValue: ValueA").append(LS)
                .append("\tValues:").append(LS)
                .append("\t\tValue1").append(LS)
                .append("\t\tValue2").append(LS);

        singleEntityTestClass = new MockEntityClass(1L, "ValueA", Arrays.asList("Value1", "Value2"));
    }

    @BeforeAll
    static void setEmptyData() {
        emptyData
                .append("# dmidecode 3.6").append(LS)
                .append("Getting SMBIOS data from sysfs.").append(LS)
                .append("SMBIOS 3.3.0 present.").append(LS).append(LS);
    }

    @BeforeAll
    static void setMultipleEntityData() {

        multipleEntityData
                .append("# dmidecode 3.6").append(LS)
                .append("Getting SMBIOS data from sysfs.").append(LS)
                .append("SMBIOS 3.3.0 present.").append(LS).append(LS)

                .append("Handle 0x0011, DMI type 4, 48 bytes").append(LS)
                .append("Processor Information").append(LS)
                .append("\tID: 1").append(LS)
                .append("\tValue: ValueA").append(LS)
                .append("\tValues:").append(LS)
                .append("\t\tValue1").append(LS)
                .append("\t\tValue2").append(LS).append(LS)

                .append("Handle 0x0011, DMI type 4, 48 bytes").append(LS)
                .append("Processor Information").append(LS)
                .append("\tID: 2").append(LS)
                .append("\tValue: ValueB").append(LS)
                .append("\tValues:").append(LS)
                .append("\t\tValue3").append(LS)
                .append("\t\tValue4").append(LS).append(LS);

        multipleEntityClassOne = new MockEntityClass(1L, "ValueA", Arrays.asList("Value1", "Value2"));

        multipleEntityClassTwo = new MockEntityClass(2L, "ValueB", Arrays.asList("Value3", "Value4"));
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
        String data = "ID: 1" + LS + "Value:" + LS + "Values: ";
        Optional<MockEntityClass> result = mapper.mapToEntity(data, MockEntityClass.class);

        assertThat(result).isPresent();
        assertThat(result).contains(new MockEntityClass(1L, null, null));
    }

    @Test
    void test_mapToEntity_empty_success() {
        Optional<MockEntityClass> testClass = mapper.mapToEntity(emptyData.toString(), MockEntityClass.class);
        assertThat(testClass).isPresent();
        assertThat(testClass).contains(new MockEntityClass()); // empty entity class with null values
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