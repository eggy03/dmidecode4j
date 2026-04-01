package io.github.eggy03.dmidecode.annotation.fragility;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     When marked on a method (or a constructor), it indicates that annotated method or constructor
 *     has a definition that invokes a method or a constructor annotated with {@code FragileMethod}
 * </p>
 * <p>
 *     This annotation is for documentation purposes only.
 * </p>
 *
 * @since 0.2.0
 * @see FragileMethod
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Repeatable(InvokesFragileMethods.class)
public @interface InvokesFragileMethod {

    /**
     * The class whose methods, marked with {@code FragileMethod}, are being invoked.
     */
    Class<?> targetClass();

    /**
     * The type of method that has been marked with {@code FragileMethod}
     */
    MethodType methodType();

    /**
     * The method names of the {@code FragileMethod} annotated types
     */
    String[] methodName() default {};
}
