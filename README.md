[![License](https://img.shields.io/github/license/eggy03/ferrumx-windows?style=for-the-badge&color=white)](https://github.com/eggy03/dmidecode4j/blob/master/LICENSE)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.eggy03/dmidecode4j?style=for-the-badge&color=pink)](https://central.sonatype.com/artifact/io.github.eggy03/dmidecode4j)
![Minimum JDK Version](https://img.shields.io/badge/Minimum%20JDK%20Version-8-blue?style=for-the-badge)

> [!NOTE]  
> The API is currently in rapid development stage.
> While the core functionalities have been tested and is considered stable,
> the public API contract may evolve continuously, in form of breaking changes, until a stable API is achieved.

# About
dmidecode4j is a lightweight, free Java library that parses human-readable `dmidecode` output and deserializes it into strongly-typed Java entities.

# Supported Operating Systems
- Any Linux distributions that support `dmidecode` will be able to use this library
> ⚠️ Root privileges are required to run dmidecode.

# CI Stats
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/eggy03/dmidecode4j/.github%2Fworkflows%2Fbuild.yml)
![Commits to main since latest release](https://img.shields.io/github/commits-since/eggy03/dmidecode4j/latest)

# Download
> **Minimum Supported Java Version:** 8

Maven:
```xml
<dependency>
    <groupId>io.github.eggy03</groupId>
    <artifactId>dmidecode4j</artifactId>
    <version>VERSION</version>
</dependency>
```

Gradle:
```gradle
implementation group: 'io.github.eggy03', name: 'dmidecode4j', version: 'VERSION'
```
> Replace `VERSION` with the latest version available in maven central

For other build ecosystems, check out the [Maven Central Repository](https://central.sonatype.com/artifact/io.github.eggy03/dmidecode4j/overview)

> [!NOTE]
> The `sources.jar` published with this library includes de-lomboked code which should prevent the IDEs from complaining
> about source mismatch between the decompiled class files and the downloaded sources. It should also make your debugging
> easier, should you step into the library code during the debugging process of your project.

# Usage
```java
public class ProcessorExample {

    public static void main(String[] args) {

        DMIProcessorService service = new DMIProcessorService();
        Optional<DMIProcessor> processor = service.get(10L);

        processor.ifPresent(System.out::println);

        // can also access individual metrics via the getters
    }
}
```

# Docs

- [Javadocs](https://javadoc.io/doc/io.github.eggy03/dmidecode4j)
- [Developer Docs](//todo)
- [Migration Guide](//todo)
- [Examples](//todo)

# Implementation Status

| Number | Type                             | Status      |
|--------|----------------------------------|-------------|
| 0      | BIOS                             | Implemented |
| 1      | SYSTEM                           | Implemented |
| 2      | BASEBOARD                        | Implemented |
| 3      | CHASSIS                          | Implemented |
| 4      | PROCESSOR                        | Implemented |
| 5      | MEMORY_CONTROLLER                | Pending     |
| 6      | MEMORY_MODULE                    | Pending     |
| 7      | CACHE                            | Implemented |
| 8      | PORT_CONNECTOR                   | Implemented |
| 9      | SYSTEM_SLOTS                     | Implemented |
| 10     | ONBOARD_DEVICES                  | Pending     |
| 11     | OEM_SETTINGS                     | Pending     |
| 12     | SYSTEM_CONFIGURATION_OPTIONS     | Pending     |
| 13     | BIOS_LANGUAGE                    | Implemented |
| 14     | GROUP_ASSOCIATIONS               | Pending     |
| 15     | SYSTEM_EVENT_LOG                 | Pending     |
| 16     | PHYSICAL_MEMORY_ARRAY            | Implemented |
| 17     | MEMORY_DEVICE                    | Implemented |
| 18     | THIRTY_TWO_BIT_MEMORY_ERROR      | Pending     |
| 19     | MEMORY_ARRAY_MAPPED_ADDRESS      | Pending     |
| 20     | MEMORY_DEVICE_MAPPED_ADDRESS     | Pending     |
| 21     | BUILT_IN_POINTING_DEVICE         | Pending     |
| 22     | PORTABLE_BATTERY                 | Implemented |
| 23     | SYSTEM_RESET                     | Pending     |
| 24     | HARDWARE_SECURITY                | Pending     |
| 25     | SYSTEM_POWER_CONTROLS            | Pending     |
| 26     | VOLTAGE_PROBE                    | Pending     |
| 27     | COOLING_DEVICE                   | Pending     |
| 28     | TEMPERATURE_PROBE                | Pending     |
| 29     | ELECTRICAL_CURRENT_PROBE         | Pending     |
| 30     | OUT_OF_BAND_REMOTE_ACCESS        | Pending     |
| 31     | BOOT_INTEGRITY_SERVICES          | Pending     |
| 32     | SYSTEM_BOOT                      | Pending     |
| 33     | SIXTY_FOUR_BIT_MEMORY_ERROR      | Pending     |
| 34     | MANAGEMENT_DEVICE                | Pending     |
| 35     | MANAGEMENT_DEVICE_COMPONENT      | Pending     |
| 36     | MANAGEMENT_DEVICE_THRESHOLD_DATA | Pending     |
| 37     | MEMORY_CHANNEL                   | Pending     |
| 38     | IPMI_DEVICE                      | Pending     |
| 39     | POWER_SUPPLY                     | Pending     |
| 40     | ADDITIONAL_INFORMATION           | Pending     |
| 41     | ONBOARD_DEVICE                   | Pending     |

# Projects Using dmidecode4j

1) [Nautilus](https://github.com/eggy03/Nautilus) - A cross-platform Swing based GUI application for displaying computer
   information

If you want to add your project to this list, simply create an [issue](https://github.com/eggy03/dmidecode/issues)
with the showcase template and provide the required information.

# License
This project is licensed under the [MIT License](/LICENSE).
