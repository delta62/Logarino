package com.samnoedel.logarino;

/**
 * A logger that emits messages to stdout and stderr. Messages with an error or warning level will be logged to stderr
 * while other messages will be logged to stdout.
 */
public class ConsoleLogger implements Logger {

    private final LogFormatter formatter;
    private LogLevel level;

    /**
     * Create a new ConsoleLogger that logs messages at or above the INFO level.
     */
    public ConsoleLogger() {
        this(LogLevel.INFO);
    }

    /**
     * Create a new ConsoleLogger that logs messages at or above the given level. The default formatter will
     * be used to format the messages.
     * @param level The minimum log level to log
     */
    public ConsoleLogger(LogLevel level) {
        this(level, new DefaultFormatter());
    }

    /**
     * Create a new ConsoleLogger that logs messages at or above the given level and uses the given formatter
     * to format each message.
     * @param level The minimum log level to log
     * @param formatter The formatter to use when logging messages
     */
    public ConsoleLogger(LogLevel level, LogFormatter formatter) {
        this.level = level;
        this.formatter = formatter;
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void debug(String message) {
        logMessage(LogLevel.DEBUG, message);
    }

    @Override
    public void info(String message) {
        logMessage(LogLevel.INFO, message);
    }

    @Override
    public void warning(String message) {
        logMessage(LogLevel.WARNING, message);
    }

    @Override
    public void error(String message) {
        logMessage(LogLevel.ERROR, message);
    }

    private void logMessage(LogLevel level, String message) {
        if (!LogLevel.shouldLog(this.level, level)) {
            return;
        }

        String formatted = formatter.format(level, message);
        if (level == LogLevel.ERROR || level == LogLevel.WARNING) {
            System.err.println(formatted);
        } else {
            System.out.println(formatted);
        }
    }
}
