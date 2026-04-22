# Changelog

All notable changes to this project will be documented in this file.

Please check out the [Releases](https://github.com/eggy03/dmidecode4j/releases) page to know more about the
commits and PRs that contributed to each of the releases.

This project tries its best to adhere to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

The following headings may be used while categorizing the list of changes made in each version:

- New Features
- Removed Features
- Bug Fixes
- Non-Breaking Changes
- Breaking Changes
- Test Changes
- Dependency Updates
- Documentation
- Known Issues

## [0.3.0] - April 22, 2026

This update introduces several breaking and non-breaking changes along with refactors only and is fully backwards
compatible with v0.2.0, if u have not used builder methods or other accessory Immutables created methods, in your
project.

For a complete list of changes, see the following PRs:

- [PR#3](https://github.com/eggy03/dmidecode4j/pull/3)
- [PR#4](https://github.com/eggy03/dmidecode4j/pull/4)
- [PR#5](https://github.com/eggy03/dmidecode4j/pull/5)

### Breaking Changes

- Fixed leaking of Immutables generated immutable implementations of abstract classes via service classes, to the public
  API

Before Immutables was introduced, entity classes were not abstract and did not have the `Abstract*` prefix either.
They were concrete classes and the service classes had these concrete classes as their return type, for example
`List<DMIProcessor> get();`. After Immutables was introduced, the entity classes were turned into abstract classes and
would have the `Abstract*`prefix in their name. Immutables would scan this and create generated immutable
implementations after removing the`Abstract*` prefix. So, an abstract class `AbstractDMIProcessor.class` would have an
immutable implementation called
`DMIProcessor.class`. This was fine, but entity naming meant the service classes should have returned the abstract
instances. Instead, the return type of the service classes retained the pre-Immutable naming scheme, which meant,
service classes would now return generated concrete implementations, instead of the actual abstract entities. This
caused a leakage of immutable entities to the public API.
It was originally intended for the service classes to de-serialize a JSON to its immutable instance but return the
abstract instance, since all immutable generated sources extend the abstract classes. If there was a need to build a
custom instance, something like the following would have been the preferred choice:

```java
DMIProcessor processor = new ImmutableDMIProcessor.Builder().build();
// or
DMIProcessor processor = ImmutableDMIProcessor.copyOf(someOtherProcessor).withSomeMethod(someValue);
```

where `DMIProcessor` represents the abstract class and `ImmutableDMIProcessor` is the generated immutable
implementation.

To achieve this, the `Abstract*` prefix from all the abstract classes have been removed, and `Immutable*` prefix has
been added to all generated immutable implementations. Additionally, two Jackson annotations called
`@JsonSerialize(as = ImmutableSomeName.class)` and `@JsonDeserialize(as = ImmutableSomeName.class)` has been added to
all the entity classes.
These annotations tell the Jackson mappers in the mapping layer to deserialize or serialize the input JSON as the
immutable implementations, but when returning the value to the service layer, which is eventually returned to the user,
it should be referenced via the abstract entity only.

`JSON -> ImmutableDMIProcessor(mapping layer) -> DMIProcessor(service layer)`

Obviously, this means you will not be able to access the builder methods, which is intended, because to create your
custom builder, you must invoke the Immutable implementations directly. The abstract reference only contains the
immutable field values.

- `toString()` in entity classes no longer returns a pretty-printed JSON. The function has been delegated to `toJson()`
  instead.

### Non-Breaking Changes

- Change `getCommandFor()` to an instance method `getCommand()`, in `DMIType`.
- Rename `TerminalUtility` to `TerminalService` and refactored it to become an instance based class from utility class
  and reduced the possibility of arbitrary command execution.
- Refactor service classes to allow dependency injection of `TerminalService` and mappers.
- Rename `DMIPortConnectionInformationMapper` to `DMIPortConnectorInformationMapper`.
- Introduce default method `configureObjectMapper()`  to allow custom ObjectMapper configuration in `CommonDMIMapper`
  interface.

### Test Changes

- TerminalUtilityTest has been renamed to TerminalServiceTest and individual test cases have been updated to test the
  current contract of TerminalService methods
- Introduce `@Mock` for `TerminalService` and `DMI[Entity]Mapper` to mock dependencies.
- Use `@InjectMocks` for `DMI[Entity]Service` to automatically inject mocked dependencies.
- Remove static mocking of `TerminalUtility` and instead mock `TerminalService` and mappers, and refactor the tests to
  verify service contract, orders, instead of verifying the output of mapped data.
- Previously, service tests would only mock `TerminalUtility` (now `TerminalService`) and the mapper would not be
  stubbed. With this overhaul, both mappers and terminal are stubbed and verification is performed based on what got
  executed instead, and not what was returned, since that is already tested in the mapping layer.

## [0.2.0] - April 06, 2026

This update introduces several breaking changes and is not backwards compatible with the previous versions.
For a complete list of changes, see the enclosed [PR#2](https://github.com/eggy03/dmidecode4j/pull/2)

### Non-Breaking Changes

- Replaced the underlying mapper from GSON to Jackson
- Replaced `DMIType.getCommand(int)` with `DMIType.getCommandFor(DMIType)` in `DMIType.java`.
- Updated all service classes to use the new `DMIType.getCommandFor()` method.

### Breaking Changes

- Removed all traces of `Lombok` and replaced them with `Immutables` equivalents for deep immutability of entity
  classes.
  As a result, the builder methods now have a slightly different syntax. The remaining changes involve writing more
  boilerplate code to make up for removal of Lombok's annotations but are non-breaking in nature.
  The complete list of changes can be found in the enclosed PR.

During Lombok, the syntax for an entity builder was as follows:

```java
import io.github.eggy03.dmidecode.entity.processor.DMIProcessor;

void main() {
    // building a new entity using builder
    DMIProcessor processor = DMIProcessor.Builder.build();


    DMIProcessor processorTwo = processor.toBuilder().build();

    // accessing fields
    processor.getCurrentSpeed();
}


```

With Immutables, the new syntax is:

```java
import io.github.eggy03.dmidecode.entity.processor.DMIProcessor;

@SuppressWarnings("all")
void main() {

    // building a new entity using builder
    DMIProcessor processor = new DMIProcessor.Builder().build();

    // updating a built entity
    DMIProcessor processorTwo = processor.withProperties();

    // accessing fields
    processor.currentSpeed();
}
```

- All entities have been converted to their abstract forms.
  Immutables handles their concrete implementation during compile time.
  This is technically not a breaking change since the generated implementations retain their `pre-0.2.0` names

- `CommonDMIMapper#mapToList` returns an unmodifiable list using `Collections.unmodifiableList`

### Test Changes

- Updated corresponding entity tests to use the `Immutables` builder pattern
- Updated `MockEntityClass` in `CommonDMIMapperTest` to use Jackson's `@JsonProperty` as a replacement for GSON's
  `@SerializedValue`.
- Removed all traces of Lombok from the tests.

### Dependency Updates

- Added Jackson and Immutables BOMs and dependencies
- Removed GSON and Lombok
- Updated source generation plugins to support packing Immutables generated code in `sources.jar`
- Updated `maven-compiler-plugin` from `3.14.1` to `3.15.0`
- Updated `maven-surefire-plugin` from `3.5.4` to `3.5.5`
- Updated `central-publishing-maven-plugin` from `0.9.0` to `0.10.0`
- Updated `assertj-core` from `3.27.6` to `3.27.7`

### Documentation

- Replaced JetBrains `@NotNull` and `@Nullable` and Lombok's `@NonNull` annotations with Jspecify equivalents.
  For `@Unmodifiable`, an equivalent custom `@Unmodifiable` annotation has been introduced.

- Introduced two new annotations `@FragileMethod` and `@InvokesFragileMethod`.
  These two annotations when used, document that a method or a constructor's implementation is fragile in nature
  and may break in the future.

- Updated Javadocs to reflect the Immutable builder style examples
- Removed `@author` tag from Javadocs
- Updated developer mail in `pom.xml`
- Corrected `project.license.url` branch from `main` to `master` in `pom.xml`
- Added an implementation status table in `README.md`

## [0.1.1] - January 14, 2026

## Breaking Changes

- `DMIProcessorService` now returns a list of `DMIProcessor` objects instead of an `Optional<DMIProcessor>` instance

## [0.1.0] - January 13, 2026

- Initial Release