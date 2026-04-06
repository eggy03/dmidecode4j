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

## [0.2.0] - April 06, 2026

This update introduces several breaking changes and is not backwards compatible with the previous versions.
For a complete list of changes, see the enclosed [PR](https://github.com/eggy03/dmidecode4j/pull/2)

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