package io.github.eggy03.dmidecode.annotation;

import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * A custom annotation that is intended to be applied with {@link Value.Immutable},
 * on {@link io.github.eggy03.dmidecode.entity} package classes.
 * </p>
 * <p>
 * It modifies the naming and structural style of the generated immutable implementations via {@link Value.Style}
 * </p>
 *
 * @since 0.2.0
 */
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@Value.Style(
        typeAbstract = {"*"}, // No prefixes or suffixes will be detected and trimmed
        typeImmutable = "Immutable*", // Generated immutable type will have Immutable prefix added
        visibility = Value.Style.ImplementationVisibility.PUBLIC, // Generated class will be always public
        builder = "new", // construct builder using 'new' instead of factory method (required for Jackson).
        // Generated builders will have attributes annotated with @JsonProperty so deserialization will work properly.
        defaults = @Value.Immutable(copy = true) // Enable copy methods
)
public @interface ImmutableEntityStyle {
}
