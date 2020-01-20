package com.samnoedel.logarino;

public interface Logger {
    void setLevel(LogLevel level);

    void debug(String message);

    void info(String message);

    void warning(String message);

    void error(String message);
}
