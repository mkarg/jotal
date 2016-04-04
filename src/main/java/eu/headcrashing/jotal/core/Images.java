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

package eu.headcrashing.jotal.core;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Utility class providing helper methods for managing {@link Image}s.
 *
 * @author Markus KARG (markus@headcrashing.eu)
 */
final class Images {

    /**
     * Converts a given {@link Image} to a {@link BufferedImage}.
     *
     * Will directly return {@code image} without any conversion in case it already is a {@code BufferedImage}.
     *
     * @param awtImage
     *            The image to be converted. May be {@code null}.
     * @return The converted {@code BufferedImage}, or {@code null} in case the provided image was {@code null}.
     */
    static final BufferedImage asBufferedImage(final Image awtImage) {
        if (awtImage == null || awtImage instanceof BufferedImage)
            return (BufferedImage) awtImage;

        final BufferedImage bufferedImage = new BufferedImage(awtImage.getWidth(null), awtImage.getHeight(null), TYPE_INT_ARGB);

        final Graphics2D graphics2D = bufferedImage.createGraphics();
        try {
            graphics2D.drawImage(awtImage, 0, 0, null);
        } finally {
            graphics2D.dispose();
        }

        return bufferedImage;
    }
}
