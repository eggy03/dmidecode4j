package io.github.eggy03.dmidecode.annotation;

import org.immutables.value.Value;
import tools.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@JsonSerialize // Jackson automatic integration
@Value.Style(
        typeAbstract = {"Abstract*"}, // 'Abstract' prefix will be detected and trimmed
        typeImmutable = "*", // No prefix or suffix for generated immutable type
        visibility = Value.Style.ImplementationVisibility.PUBLIC, // Generated class will be always public
        builder = "new", // construct builder using 'new' instead of factory method (required for Jackson).
        // Generated builders will have attributes annotated with @JsonProperty so deserialization will work properly.
        defaults = @Value.Immutable(copy = true) // Enable copy methods
)
public @interface ImmutableStyle {}
