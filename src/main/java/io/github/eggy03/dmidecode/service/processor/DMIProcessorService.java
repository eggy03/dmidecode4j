package io.github.eggy03.dmidecode.service.processor;

import io.github.eggy03.dmidecode.constant.DMIType;
import io.github.eggy03.dmidecode.entity.processor.DMIProcessor;
import io.github.eggy03.dmidecode.mapper.processor.DMIProcessorMapper;
import io.github.eggy03.dmidecode.service.OptionalCommonDMIServiceInterface;
import io.github.eggy03.dmidecode.utility.TerminalUtility;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DMIProcessorService implements OptionalCommonDMIServiceInterface<DMIProcessor> {

    @Override
    @NotNull
    public Optional<DMIProcessor> get() {
        return new DMIProcessorMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PROCESSOR.getValue()), 10),
                DMIProcessor.class
        );
    }

    @Override
    @NotNull
    public Optional<DMIProcessor> get(long timeout) {
        return new DMIProcessorMapper().mapToEntity(
                TerminalUtility.executeCommand(DMIType.getCommand(DMIType.PROCESSOR.getValue()), timeout),
                DMIProcessor.class
        );
    }

}
