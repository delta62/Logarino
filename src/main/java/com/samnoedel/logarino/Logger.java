package com.samnoedel.logarino;

/**
 * A logger which can log messages of varying severity and change the log level dynamically
 */
public interface Logger {

    /**
     * Set the level at which this logger will log. Messages with a lower priority than set here will not be logged.
     * @param level The minimum message level that should be logged
     */
    void setLevel(LogLevel level);

    /**
     * Log a debug message. Typically used for deep debugging and not intended for production use.
     * @param message The message to log
     */
    void debug(String message);

    /**
     * Log an info message. Typically used to document important events that are expected to happen.
     * @param message The message to log
     */
    void info(String message);

    /**
     * Log a warning message. Typically used to document that something potentially unexpected has happened.
     * @param message The message to log
     */
    void warning(String message);

    /**
     * Log an error message. Typically used to document a failure within the software.
     * @param message The message to log
     */
    void error(String message);
}
