package io.github.eggy03.dmidecode.annotation.fragility;

/**
 * Enum to classify the type of method that is marked as fragile
 *
 * @see FragileMethod
 * @see InvokesFragileMethod
 * @since 0.2.0
 */
public enum MethodType {

    /**
     * Indicates that an instance method is marked as {@link FragileMethod}
     */
    INSTANCE_METHOD,

    /**
     * Indicates that a static method is marked as {@link FragileMethod}
     */
    STATIC_METHOD,

    /**
     * Indicates that an abstract method from an abstract class or an interface is marked as {@link FragileMethod}
     */
    ABSTRACT_METHOD,

    /**
     * Indicates that a default method from an interface is marked as {@link FragileMethod}
     */
    INTERFACE_DEFAULT_METHOD,


    /**
     * Indicates that a constructor is marked as {@link FragileMethod}
     */
    CONSTRUCTOR
}
