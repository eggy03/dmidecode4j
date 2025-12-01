package io.github.eggy03.dmidecode.service;

import java.util.List;

public interface CommonDMIServiceInterface<S> {

    List<S> get();

    List<S> get(long timeout);
}
