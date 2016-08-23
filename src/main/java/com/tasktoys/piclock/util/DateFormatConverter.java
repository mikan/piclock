/*
 * Cpoyright (c) 2016 mikan. All rights reserved.
 */
package com.tasktoys.piclock.util;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A {@link StringConverter} extend for any custom {@link LocalDate} format.
 *
 * @author mikan
 * @since 1.0
 */
public class DateFormatConverter extends StringConverter<LocalDate> {

    private DateTimeFormatter dateFormatter;

    public DateFormatConverter(String pattern) {
        if (pattern == null) {
             throw new NullPointerException("pattern is null.");
        }
        dateFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public String toString(LocalDate date) {
        if (date != null) {
            return dateFormatter.format(date);
        } else {
            return "";
        }
    }

    @Override
    public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dateFormatter);
        } else {
            return null;
        }
    }
}
