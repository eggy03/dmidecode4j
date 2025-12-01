package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBIOSLanguage;
import io.github.eggy03.dmidecode.mapper.board.DMIBIOSLanguageMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIService;
import io.github.eggy03.dmidecode.utility.TerminalUtility;

import java.util.Optional;

public class DMIBIOSLanguageService implements OptionalCommonDMIService<DMIBIOSLanguage> {

    @Override
    public Optional<DMIBIOSLanguage> get() {
        return new DMIBIOSLanguageMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BIOS_LANGUAGE.getValue()), 60),
                DMIBIOSLanguage.class
        );
    }
}