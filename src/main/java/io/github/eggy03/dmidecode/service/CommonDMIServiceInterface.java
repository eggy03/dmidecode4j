/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service;

import java.util.List;

/**
 * Common service interface whose method implementations provide a way to fetch
 * DMI information from the system using the {@code dmidecode} utility
 * in the form of a {@link List}.
 * <p>
 * Useful for implementing services that return more than one DMI structure,
 * such as CPU Caches, Memory Device.
 * </p>
 *
 * @param <S> the entity type returned by the service implementation
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 * @see OptionalCommonDMIServiceInterface
 */
public interface CommonDMIServiceInterface<S> {

    /**
     * Implementations of this method are expected to execute the corresponding
     * {@code dmidecode} command and then map the output to the expected entity types.
     *
     * @param timeout the maximum time (in seconds) to wait for the
     *                {@code dmidecode} command to complete before
     *                terminating the process
     * @return a {@link List} of entities of type {@code <S>} defined by the caller
     *
     * @since 0.1.0
     */
    List<S> get(long timeout);
}
