package io.github.eggy03.dmidecode.annotation.fragility;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *      When marked on a method (or a constructor), it serves as an indication that
 *      the method's behavior depends on unstable and or environment-specific logic
 *      and may break without notice.
 * </p>
 * <p>
 *    A method annotated with {@code @FragileMethod} should not be used in production.
 *    However, if usage in production is unavoidable, any method or constructor that
 *    invokes a fragile method in its definition should be annotated with {@code @InvokesFragileMethod}.
 * </p>
 * <p>
 *     This annotation is for documentation purposes only.
 * </p>
 *
 * @since 0.2.0
 * @see InvokesFragileMethod
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface FragileMethod {

    /**
     * The type of method marked with this annotation
     */
    MethodType type();

    /**
     * The reason why the element is marked with this annotation
     */
    String reason();

    /**
     * Indicates whether the method needs to be replaced with a better logic
     */
    boolean requiresReplacement() default false;

    /**
     * States any possible solutions to the fragility problem
     */
    String replacementNote() default "";

}
