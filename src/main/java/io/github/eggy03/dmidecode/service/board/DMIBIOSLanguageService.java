package io.github.eggy03.dmidecode.service.board;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.board.DMIBIOSLanguage;
import io.github.eggy03.dmidecode.mapper.board.DMIBIOSLanguageMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DMIBIOSLanguageService implements OptionalCommonDMIServiceInterface<DMIBIOSLanguage> {

    @Override
    @NotNull
    public Optional<DMIBIOSLanguage> get() {
        return new DMIBIOSLanguageMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BIOS_LANGUAGE.getValue()), 10),
                DMIBIOSLanguage.class
        );
    }

    @Override
    @NotNull
    public Optional<DMIBIOSLanguage> get(long timeout) {
        return new DMIBIOSLanguageMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.BIOS_LANGUAGE.getValue()), timeout),
                DMIBIOSLanguage.class
        );
    }
}