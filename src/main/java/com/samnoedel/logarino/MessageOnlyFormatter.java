package com.samnoedel.logarino;

/**
 * A log formatter that only outputs the log message with no metadata included in it.
 */
public class MessageOnlyFormatter implements LogFormatter {
    @Override
    public String format(LogLevel level, String message) {
        return message;
    }
}
