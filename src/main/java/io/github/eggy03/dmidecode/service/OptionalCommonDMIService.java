package io.github.eggy03.dmidecode.service;

import java.util.Optional;

public interface OptionalCommonDMIService<S> {
    Optional<S> get();
}
