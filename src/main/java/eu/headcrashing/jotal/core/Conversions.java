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

import static eu.headcrashing.jotal.core.Images.asBufferedImage;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.imageio.ImageIO;

/**
 * Data type conversion helpers
 *
 * @author Markus KARG (markus@headcrashing.eu)
 */
public final class Conversions {

    /**
     * Converts {@code Path} → {@code String}
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final String asString(final Path value) {
        return value.toString();
    }

    /**
     * Converts {@code String} → {@code Path}
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final Path asPath(final String value) {
        return Paths.get(value);
    }

    /**
     * Converts {@code Path} → {@code URI}
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final URI asURI(final Path value) {
        return value.toUri();
    }

    /**
     * Converts {@code URI} → {@code Path}
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final Path asPath(final URI value) {
        return Paths.get(value);
    }

    /**
     * Converts {@code Image} → PNG {@code byte[]}
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final byte[] asPngByteArray(final Image value) throws IOException {
        return asByteArray(value, "png");
    }

    /**
     * Converts {@code Image} → {@code byte[]} of specified format (e. g. {@code "png"})
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final byte[] asByteArray(final Image value, final String formatName) throws IOException {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            if (ImageIO.write(asBufferedImage(value), formatName, outputStream))
                return outputStream.toByteArray();
            throw new UnsupportedOperationException(format("No writer found for provided format name '%s'.", formatName));
        }
    }

    /**
     * Converts {@code byte[]} → {@code Image}
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final Image asImage(final byte[] value) throws IOException {
        try (final InputStream inputStream = new ByteArrayInputStream(value)) {
            return ofNullable(ImageIO.read(inputStream)).orElseThrow(() -> new UnsupportedOperationException("No suitable reader found for provided bitmap."));
        }
    }

    /**
     * Converts local {@code LocalDate} → {@code Instant} at <u>default</u> timezone.
     *
     * <b>Warning!</b>
     * <em>This conversion implies data modification, as {@code value} is interpreted as "at midnight in the <u>local</u> timezone", what not necessarily is wanted in some cases.</em>
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final Instant asLocalMidnightInstant(final LocalDate value) {
        return value.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    /**
     * Converts {@code Instant} → {@code LocalDate}.
     *
     * <b>Warning!</b> <em>This conversion implies data loss, as {@code value}'s time and time zone are <em>removed</em>, what not necessarily is wanted in some
     * cases.</em>
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final LocalDate asLocalMidnightLocalDate(final Instant value) {
        return LocalDate.from(value);
    }

    /**
     * Converts local {@code java.sql.Date} → {@code Instant} at <u>default</u> timezone.
     *
     * <b>Warning!</b>
     * <em>This conversion implies data modification, as {@code value} is interpreted as "at midnight in the <u>local</u> timezone", what not necessarily is wanted in some cases.</em>
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final Instant asLocalMidnightInstant(final java.sql.Date value) {
        return asLocalMidnightInstant(asLocalDate(value));
    }

    /**
     * Converts {@code Instant} → {@code java.sql.Date}.
     *
     * <b>Warning!</b> <em>This conversion implies data loss, as {@code value}'s time and time zone are <em>removed</em>, what not necessarily is wanted in some
     * cases.</em>
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final java.sql.Date asLocalMidnightSqlDate(final Instant value) {
        return asSqlDate(asLocalMidnightLocalDate(value));
    }

    /**
     * Converts local {@code java.sql.Date} → {@code LocalDate}.
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final LocalDate asLocalDate(final java.sql.Date value) {
        return value.toLocalDate();
    }

    /**
     * Converts {@code LocalDate} → {@code java.sql.Date}.
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final java.sql.Date asSqlDate(final LocalDate value) {
        return java.sql.Date.valueOf(value);
    }

    /**
     * Converts local {@code java.sql.Timestamp} → {@code LocalDateTime}.
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final LocalDateTime asLocalDateTime(final java.sql.Timestamp value) {
        return value.toLocalDateTime();
    }

    /**
     * Converts {@code LocalDateTime} → {@code java.sql.Timestamp}.
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final java.sql.Timestamp asSqlTimestamp(final LocalDateTime value) {
        return java.sql.Timestamp.valueOf(value);
    }

    /**
     * Converts local {@code java.sql.Timestamp} → {@code Instant} at <u>default</u> timezone.
     *
     * <b>Warning!</b>
     * <em>This conversion implies data modification, as {@code value} is interpreted as "in the <u>local</u> timezone", what not necessarily is wanted in some cases.</em>
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final Instant asLocalInstant(final java.sql.Timestamp value) {
        return asLocalDateTime(value).atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * Converts {@code Instant} → {@code java.sql.Timestamp}.
     *
     * <b>Warning!</b> <em>This conversion implies data loss, as {@code value}'s time zone is <em>removed</em>, what not necessarily is wanted in some
     * cases.</em>
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final java.sql.Timestamp asLocalSqlTimestamp(final Instant value) {
        return asSqlTimestamp(LocalDateTime.ofInstant(value, ZoneId.systemDefault()));
    }

    /**
     * Converts local {@code Integer} → {@code Duration} in <u>minutes</u>.
     *
     * <b>Warning!</b>
     * <em>This conversion implies data modification, as {@code value} is interpreted as "in <u>minutes</u>, what not necessarily is wanted in some cases.</em>
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final Duration asMinutesDuration(final int value) {
        return Duration.ofMinutes(value);
    }

    /**
     * Converts {@code Duration} → {@code Integer}.
     *
     * <b>Warning!</b> <em>This conversion implies data loss, as {@code value}'s unit is <em>removed</em> and truncated to {@code int}, what not necessarily is
     * wanted in some cases.</em>
     *
     * @param value
     *            Must not be {@code null}
     */
    public static final int asMinutesInt(final Duration value) {
        return (int) value.toMinutes();
    }
}
