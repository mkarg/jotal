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

import java.awt.Image;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import eu.headcrashing.jotal.core.Conversions;

/**
 * Converts {@code Image} (Java) ↔ {@code byte[]} (XML)
 *
 * @author Markus KARG (markus@headcrashing.eu)
 */
public final class ImageXmlAdapter extends XmlAdapter<byte[], Image> {
    @Override
    public final Image unmarshal(final byte[] value) throws Exception {
        return value == null ? null : Conversions.asImage(value);
    }

    @Override
    public final byte[] marshal(final Image value) throws Exception {
        return value == null ? null : Conversions.asPngByteArray(value);
    }
}
