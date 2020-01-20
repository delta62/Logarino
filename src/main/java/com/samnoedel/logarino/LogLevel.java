package com.samnoedel.logarino;

/**
 * LogLevels represent the severities a log message can have.
 */
public enum LogLevel {
    /**
     * A debug message is typically intended for development or debugging purposes and usually shouldn't be enabled in
     * production.
     */
    DEBUG,

    /**
     * An info message is intended to document that an expected event has occurred and is intended to be enabled in
     * production environments.
     */
    INFO,

    /**
     * A warning message is used to document something potentially problematic or unexpected occurred, but not severe
     * enough to halt processing
     */
    WARNING,

    /**
     * An error message is used to document a fatal or unrecoverable error that may result in a crash or premature
     * program halt.
     */
    ERROR;

    /**
     * Given a configured log level, determines if a message with the given messageLevel should be logged or not
     * @param configuration The configured minimum bar level of messages that should be logged
     * @param messageLevel The level of message that we're wondering should be logged or not
     * @return true if the message with messageLevel should be logged, or false otherwise
     */
    public static boolean shouldLog(LogLevel configuration, LogLevel messageLevel) {
        return configuration.ordinal() <= messageLevel.ordinal();
    }
}
