package io.github.eggy03.dmidecode.mapper.processor;

import io.github.eggy03.dmidecode.entity.processor.DMIProcessor;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

public class DMIProcessorMapper implements CommonDMIMapper<DMIProcessor> {
    @Override
    public Class<DMIProcessor> getType() {
        return DMIProcessor.class;
    }
}
