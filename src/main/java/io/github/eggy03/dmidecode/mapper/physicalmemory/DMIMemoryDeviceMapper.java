package io.github.eggy03.dmidecode.mapper.physicalmemory;

import io.github.eggy03.dmidecode.entity.physicalmemory.DMIMemoryDevice;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMIMemoryDeviceMapper implements CommonDMIMapper<DMIMemoryDevice> {
    @Override
    public Class<DMIMemoryDevice> getType() {
        return DMIMemoryDevice.class;
    }
}
