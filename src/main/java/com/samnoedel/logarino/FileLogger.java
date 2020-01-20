package com.samnoedel.logarino;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {

    private final BufferedWriter writer;
    private final LogFormatter formatter;
    private LogLevel level;

    public FileLogger(String logFilePath) throws IOException {
        this(logFilePath, LogLevel.INFO);
    }

    public FileLogger(String logFilePath, LogLevel level) throws IOException {
        this(logFilePath, level, new DefaultFormatter());
    }

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
        String formatted = formatter.format(level, message);
        try {
            writer.write(formatted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
