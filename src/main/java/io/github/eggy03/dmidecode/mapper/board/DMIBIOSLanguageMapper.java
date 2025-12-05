package io.github.eggy03.dmidecode.mapper.board;

import io.github.eggy03.dmidecode.entity.board.DMIBIOSLanguage;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMIBIOSLanguageMapper implements CommonDMIMapper<DMIBIOSLanguage> {
    @Override
    public Class<DMIBIOSLanguage> getType() {
        return DMIBIOSLanguage.class;
    }
}
