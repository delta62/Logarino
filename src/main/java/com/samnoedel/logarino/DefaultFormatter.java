package com.samnoedel.logarino;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultFormatter implements  LogFormatter {

    private static final SimpleDateFormat ISO_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public String format(LogLevel level, String message) {
        String isoTime = ISO_FORMAT.format(new Date());
        return String.format("[%s][%7s]: %s", isoTime, level.toString(), message);
    }
}
