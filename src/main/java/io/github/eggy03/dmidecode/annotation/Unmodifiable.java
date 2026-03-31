package io.github.eggy03.dmidecode.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     Indicates that the underlying `Collection` or `Map` is unmodifiable.
 *     Any attempts to mutate such a collection or map may result in exceptions being
 *     thrown, or no result at all.
 * </p>
 * <p>
 *     The referenced objects within the collection or map may still be mutable, depending on
 *     their implementation.
 * </p>
 *
 * @since 0.2.0
 */
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Unmodifiable {
}
