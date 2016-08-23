/*
 * Cpoyright (c) 2016 mikan. All rights reserved.
 */
package com.tasktoys.piclock.util;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test {@link DateFormatConverter}.
 *
 * @author mikan
 * @since 1.0
 */
public class DateFormatConverterTest {

    @Test(expected = NullPointerException.class)
    public void testConstructor_NPE() {
        new DateFormatConverter(null); // NPE expected
        fail(); // Unreachable
    }

    @Test
    public void testFromString_normalInput() {
        DateFormatConverter sut = new DateFormatConverter("yyyy/MM/dd");
        LocalDate actual = sut.fromString("1988/04/12");
        assertEquals(actual, LocalDate.of(1988, 4, 12));
    }

    @Test(expected = DateTimeParseException.class)
    public void testFromString_DTPE() {
        DateFormatConverter sut = new DateFormatConverter("");
        sut.fromString("1988/04/12"); // Cannot parse
        fail(); // Unreachable
    }

    @Test
    public void testToString_normalInput() {
        DateFormatConverter sut = new DateFormatConverter("yyyy/MM/dd");
        assertEquals(sut.toString(LocalDate.of(1988, 4, 12)), "1988/04/12");
    }
}
