package io.github.eggy03.dmidecode.service.cache;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.cache.DMICache;
import io.github.eggy03.dmidecode.mapper.cache.DMICacheMapper;
import io.github.eggy03.dmidecode.service.CommonDMIService;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DMICacheService implements CommonDMIService<DMICache> {

    @Override
    @NotNull
    public List<DMICache> get() {
        return new DMICacheMapper().mapToList(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.CACHE.getValue()), 10),
                DMICache.class
        );
    }
}
