package com.samnoedel.logarino;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A logger that emits messages to a file. All messages will be appended to the same file regardless of severity.
 * Note that writing to disk is done synchronously, so excessive use of this logger will result in performance
 * degradation.
 *
 * It is possible for the logfile to become inaccessible partway through program operation. In this case the message
 * will be printed to err along with a stack trace of why the file couldn't be written to.
 */
public class FileLogger implements Logger {

    private final BufferedWriter writer;
    private final LogFormatter formatter;
    private LogLevel level;

    /**
     * Create a new FileLogger that will log messages to the file at logFilePath. The default formatter will be used to
     * format the messages.
     * @param logFilePath The path to the file to write to. It is assumed that this file exists and is writable by the
     *                    current process.
     * @throws IOException If the given logFilePath doesn't exist or isn't writeable
     */
    public FileLogger(String logFilePath) throws IOException {
        this(logFilePath, LogLevel.INFO);
    }

    /**
     * Create a new FileLogger that will log messages with the given severity or higher to the file at logFilePath. The
     * default formatter will be used to format the messages.
     * @param logFilePath The path to the file to write to. It is assumed that this file exists and is writable by the
     *                    current process.
     * @param level The initial log level for this logger
     * @throws IOException If the given logFilePath doesn't exist or isn't writeable
     */
    public FileLogger(String logFilePath, LogLevel level) throws IOException {
        this(logFilePath, level, new DefaultFormatter());
    }

    /**
     * Create a new FileLogger that will log messages with the given severity or higher to the file at logFilePath. The
     * given formatter will be used to format the messages.
     * @param logFilePath The path to the file to write to. It is assumed that this file exists and is writable by the
     *                    current process.
     * @param level The initial log level for this logger
     * @param formatter The formatter to use before writing messages
     * @throws IOException If the given logFilePath doesn't exist or isn't writeable
     */
    public FileLogger(String logFilePath, LogLevel level, LogFormatter formatter) throws IOException {
        File file = new File(logFilePath);
        if (!file.canWrite()) {
            throw new IOException(String.format("Unable to open file for writing (%s)", logFilePath));
        }

        this.level = level;
        this.formatter = formatter;
        FileWriter fw = new FileWriter(file);
        writer = new BufferedWriter(fw);
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void debug(String message) {
        tryLogMessage(LogLevel.DEBUG, message);
    }

    @Override
    public void info(String message) {
        tryLogMessage(LogLevel.INFO, message);
    }

    @Override
    public void warning(String message) {
        tryLogMessage(LogLevel.WARNING, message);
    }

    @Override
    public void error(String message) {
        tryLogMessage(LogLevel.ERROR, message);
    }

    private void tryLogMessage(LogLevel level, String message) {
        if (!LogLevel.shouldLog(this.level, level)) {
            return;
        }

        String formatted = formatter.format(level, message);
        try {
            writer.write(formatted + '\n');
        } catch (IOException e) {
            System.err.println(String.format("Unable to log message to file: %s", formatted));
            e.printStackTrace();
        }
    }
}
