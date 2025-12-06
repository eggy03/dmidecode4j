package io.github.eggy03.dmidecode.mapper.peripheral;

import io.github.eggy03.dmidecode.entity.peripheral.DMIPortableBattery;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMIPortableBatteryMapper implements CommonDMIMapper<DMIPortableBattery> {

    @Override
    public Class<DMIPortableBattery> getType() {
        return DMIPortableBattery.class;
    }
}
