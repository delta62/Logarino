package com.samnoedel.logarino;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageOnlyFormatterTest {

    LogFormatter formatter = new MessageOnlyFormatter();

    @Test
    public void format_givenString_returnsInput() {
        String input = "some message";
        assertEquals(input, formatter.format(LogLevel.INFO, input));
    }
}