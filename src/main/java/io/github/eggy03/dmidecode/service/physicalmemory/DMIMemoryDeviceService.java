package io.github.eggy03.dmidecode.service.physicalmemory;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.physicalmemory.DMIMemoryDevice;
import io.github.eggy03.dmidecode.mapper.physicalmemory.DMIMemoryDeviceMapper;
import io.github.eggy03.dmidecode.service.CommonDMIService;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DMIMemoryDeviceService implements CommonDMIService<DMIMemoryDevice> {

    @Override
    @NotNull
    public List<DMIMemoryDevice> get() {
        return new DMIMemoryDeviceMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.MEMORY_DEVICE.getValue()), 60),
                DMIMemoryDevice.class
        );
    }
}
