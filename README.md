[![License](https://img.shields.io/github/license/eggy03/ferrumx-windows?style=for-the-badge&color=white)](https://github.com/eggy03/dmidecode4j/blob/master/LICENSE)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.eggy03/dmidecode4j?style=for-the-badge&color=pink)](https://central.sonatype.com/artifact/io.github.eggy03/dmidecode4j)
![Minimum JDK Version](https://img.shields.io/badge/Minimum%20JDK%20Version-8-blue?style=for-the-badge)

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
# License
This project is licensed under the [MIT License](/LICENSE).

# FAQ
1) How can I contribute to this project ?
   
   A: You cannot :(

---   

2) Why can I not contribute to this project ?
   
   A: Skill issue....
   Not *yours* but mine. I haven't set up any docs, examples or contribution guidelines yet (✿◠‿◠)

---

3) When can I start contributing to this horrible codebase then ?

   A: hahaaaaaaaaaaaa.....uhhh.....perhaps when i set everything up ٩(^‿^)۶

---

4) Can I criticize you for your bad code ?

   A: No :( I'm sensitive

---

5) Does this library even do its job ?

   A: Loook..all u need to know that I said it works. U can trust me ¯\(°_o)/¯
