package com.samnoedel.logarino;

/**
 * A LogFormatter specifies how log messages should be formatted before they are ultimately logged to their destination.
 */
public interface LogFormatter {
    /**
     * Format the given message into the exact format that should be logged
     * @param level The log level that the message will be logged as
     * @param message The message text
     * @return A new string with the desired formatting applied
     */
    String format(LogLevel level, String message);
}
