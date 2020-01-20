package com.samnoedel.logarino;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogLevelTest {

    @Test
    public void shouldLog_givenEqualLogLevels_returnsTrue() {
        assertTrue(LogLevel.shouldLog(LogLevel.INFO, LogLevel.INFO));
    }

    @Test
    public void shouldLog_givenLesserConfig_returnsTrue() {
        assertTrue(LogLevel.shouldLog(LogLevel.INFO, LogLevel.WARNING));
    }

    @Test
    public void shouldLog_givenGreaterConfig_returnsFalse() {
        assertFalse(LogLevel.shouldLog(LogLevel.WARNING, LogLevel.INFO));
    }
}