package io.github.eggy03.dmidecode.service.peripheral;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.peripheral.DMIPortableBattery;
import io.github.eggy03.dmidecode.mapper.peripheral.DMIPortableBatteryMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.List;

public class DMIPortableBatteryService implements CommonDMIServiceInterface<DMIPortableBattery> {
    @Override
    public List<DMIPortableBattery> get() {
        return new DMIPortableBatteryMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PORTABLE_BATTERY.getValue()), 10)
        );
    }

    @Override
    public List<DMIPortableBattery> get(long timeout) {
        return new DMIPortableBatteryMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PORTABLE_BATTERY.getValue()), timeout)
        );
    }
}
