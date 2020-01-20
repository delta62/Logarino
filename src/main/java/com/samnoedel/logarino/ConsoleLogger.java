package com.samnoedel.logarino;

public class ConsoleLogger implements Logger {

    private final LogFormatter formatter;
    private LogLevel level;

    public ConsoleLogger() {
        this(LogLevel.INFO);
    }

    public ConsoleLogger(LogLevel level) {
        this(level, new DefaultFormatter());
    }

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
        if (level == LogLevel.ERROR) {
            System.err.println(formatted);
        } else {
            System.out.println(formatted);
        }
    }
}
