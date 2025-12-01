package io.github.eggy03.dmidecode.service;

import java.util.Optional;

public interface OptionalCommonDMIServiceInterface<S> {

    Optional<S> get();

    Optional<S> get(long timeout);
}
