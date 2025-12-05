package io.github.eggy03.dmidecode.mapper.physicalmemory;

import io.github.eggy03.dmidecode.entity.physicalmemory.DMIPhysicalMemoryArray;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMIPhysicalMemoryArrayMapper implements CommonDMIMapper<DMIPhysicalMemoryArray> {
    @Override
    public Class<DMIPhysicalMemoryArray> getType() {
        return DMIPhysicalMemoryArray.class;
    }
}
