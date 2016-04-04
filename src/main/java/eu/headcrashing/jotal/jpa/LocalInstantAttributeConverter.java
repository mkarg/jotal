/*
 * #%L
 * jotal
 * %%
 * Copyright (C) 2015 - 2016 Head Crashing Informatics
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

package eu.headcrashing.jotal.jpa;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import eu.headcrashing.jotal.core.Conversions;

/**
 * Converts {@code Instant} (Java) ↔ {@code Timestamp} (SQL) with the assumption of "local", i. e. only converts the date and time parts, but not the time zone
 * part.
 *
 * @author Markus KARG (markus@headcrashing.eu)
 */
@Converter(autoApply = true)
public final class LocalInstantAttributeConverter implements AttributeConverter<Instant, Timestamp> {
    @Override
    public final Timestamp convertToDatabaseColumn(final Instant value) {
        return value == null ? null : Conversions.asLocalSqlTimestamp(value);
    }

    @Override
    public final Instant convertToEntityAttribute(final Timestamp value) {
        return value == null ? null : Conversions.asLocalInstant(value);
    }
}