/*
 * Cpoyright (c) 2016 mikan. All rights reserved.
 */
package com.tasktoys.piclock.util;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test {@link Logs}.
 *
 * @author mikan
 * @since 1.0
 */
public class LogsTest {

    @Test
    public void testApplyLogLevel_normalInput() {
        Logger logger = Logger.getLogger("TEST");
        logger.setLevel(Level.SEVERE);
        Logs.applyLogLevel(logger);
        assertNotEquals(Level.SEVERE, logger.getLevel()); // Changes expected
        assertTrue(logger.getHandlers().length > 0);
    }
}
