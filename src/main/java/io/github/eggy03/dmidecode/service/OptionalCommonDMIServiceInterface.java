/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.service;

import java.util.Optional;

/**
 * Common service interface whose method implementations provide a way to fetch
 * a single DMI structure from the system using the {@code dmidecode} utility
 * in the form of an {@link Optional}.
 * <p>
 * Useful for implementing services that are expected to return at most one
 * DMI structure, such as system information, processor details, or chassis data.
 * </p>
 *
 * @param <S> the entity type returned by the service implementation
 *
 * @since 0.1.0
 * @author Sayan Bhattacharya
 * @see CommonDMIServiceInterface
 */
public interface OptionalCommonDMIServiceInterface<S> {

    /**
     * Implementations of this method are expected to execute the corresponding
     * {@code dmidecode} command and then
     * map the output to the expected entity type.
     *
     * @param timeout the maximum time (in seconds) to wait for the
     *                {@code dmidecode} command to complete before
     *                terminating the process
     * @return an {@link Optional} containing the entity of type {@code <S>}
     *         if present, or {@link Optional#empty()} if the information
     *         is unavailable or not reported by the system
     *
     * @since 0.1.0
     */
    Optional<S> get(long timeout);
}
