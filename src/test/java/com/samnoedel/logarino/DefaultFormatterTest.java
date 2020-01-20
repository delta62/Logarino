package com.samnoedel.logarino;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultFormatterTest {

    private DefaultFormatter formatter = new DefaultFormatter();

    @Test
    public void format_debugLevel_includesDebugLevel() {
        String result = formatter.format(LogLevel.DEBUG, "Diagnostic info");
        assertTrue(result.contains("DEBUG"));
    }

    @Test
    public void format_infoLevel_includesInfoLevel() {
        String result = formatter.format(LogLevel.INFO, "Something interesting happened");
        assertTrue(result.contains("INFO"));
    }

    @Test
    public void format_warningLevel_includesWarningLevel() {
        String result = formatter.format(LogLevel.WARNING, "There could be a problem");
        assertTrue(result.contains("WARNING"));
    }

    @Test
    public void format_errorLevel_includesErrorLevel() {
        String result = formatter.format(LogLevel.ERROR, "There was a problem");
        assertTrue(result.contains("ERROR"));
    }

    @Test
    public void format_allMessages_includeIsoTimestamp() {
        String result = formatter.format(LogLevel.INFO, "Some message");
        assertTrue(result.matches(".*\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*"));
    }

    @Test
    public void format_allMessages_includeMessage() {
        String result = formatter.format(LogLevel.INFO, "Hello there");
        assertTrue(result.contains("Hello there"));
    }
}
