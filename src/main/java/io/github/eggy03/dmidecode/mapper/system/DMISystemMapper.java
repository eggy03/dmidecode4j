package io.github.eggy03.dmidecode.mapper.system;

import io.github.eggy03.dmidecode.entity.system.DMISystem;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMISystemMapper implements CommonDMIMapper<DMISystem> {
    @Override
    public Class<DMISystem> getType() {
        return DMISystem.class;
    }
}
