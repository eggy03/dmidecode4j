package io.github.eggy03.dmidecode.service.processor;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.processor.DMICache;
import io.github.eggy03.dmidecode.mapper.processor.DMICacheMapper;
import io.github.eggy03.dmidecode.service.CommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DMICacheService implements CommonDMIServiceInterface<DMICache> {

    @Override
    @NotNull
    public List<DMICache> get() {
        return new DMICacheMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.CACHE.getValue()), 10),
                DMICache.class
        );
    }

    @Override
    @NotNull
    public List<DMICache> get(long timeout) {
        return new DMICacheMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.CACHE.getValue()), timeout),
                DMICache.class
        );
    }
}
