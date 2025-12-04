package io.github.eggy03.dmidecode.service.physicalmemory;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.physicalmemory.DMIPhysicalMemoryArray;
import io.github.eggy03.dmidecode.mapper.physicalmemory.DMIPhysicalMemoryArrayMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.Optional;

public class DMIPhysicalMemoryArrayService implements OptionalCommonDMIServiceInterface<DMIPhysicalMemoryArray> {
    @Override
    public Optional<DMIPhysicalMemoryArray> get() {
        return new DMIPhysicalMemoryArrayMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PHYSICAL_MEMORY_ARRAY.getValue()), 10),
                DMIPhysicalMemoryArray.class
        );
    }

    @Override
    public Optional<DMIPhysicalMemoryArray> get(long timeout) {
        return new DMIPhysicalMemoryArrayMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PHYSICAL_MEMORY_ARRAY.getValue()), timeout),
                DMIPhysicalMemoryArray.class
        );
    }
}
