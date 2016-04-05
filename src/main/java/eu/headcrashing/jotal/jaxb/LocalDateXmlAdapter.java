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

package eu.headcrashing.jotal.jaxb;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts {@code LocalDate} (Java) ↔ {@code String} (XML) according ISO 8601.
 *
 * @author Markus KARG (markus@headcrashing.eu)
 */
public final class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public final LocalDate unmarshal(final String value) throws Exception {
        return value == null ? null : LocalDate.parse(value);
    }

    @Override
    public final String marshal(final LocalDate value) throws Exception {
        return value == null ? null : value.toString();
    }
}