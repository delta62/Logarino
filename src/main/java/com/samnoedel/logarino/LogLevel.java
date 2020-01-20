package com.samnoedel.logarino;

public enum LogLevel {
    DEBUG,
    INFO,
    WARNING,
    ERROR;

    public static boolean shouldLog(LogLevel configuration, LogLevel messageLevel) {
        return configuration.ordinal() >= messageLevel.ordinal();
    }
}
