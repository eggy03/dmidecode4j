/*
 * © 2026 The dmidecode4j contributors
 * Licensed under the MIT License.
 * See the LICENSE file in the project root for more information.
 */
package io.github.eggy03.dmidecode.mapper.board;

import io.github.eggy03.dmidecode.entity.board.DMIPortConnectorInformation;
import io.github.eggy03.dmidecode.mapper.CommonDMIMapper;

/**
 * Provides a type-safe implementation of {@link CommonDMIMapper}
 * and maps raw {@code dmidecode} output to objects or lists of {@link DMIPortConnectorInformation}.
 *
 * @since 0.1.0
 */
public class DMIPortConnectionInformationMapper implements CommonDMIMapper<DMIPortConnectorInformation> {
}
