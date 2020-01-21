package com.samnoedel.logarino;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsoleLoggerTest {

    static PrintStream systemOut;
    static PrintStream systemErr;

    ByteArrayOutputStream stdout;
    ByteArrayOutputStream stderr;

    @BeforeClass
    public static void setUpClass() {
        systemOut = System.out;
        systemErr = System.err;
    }

    @Before
    public void setUp() {
        stdout = new ByteArrayOutputStream();
        stderr = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));
        System.setErr(new PrintStream(stderr));
    }

    @AfterClass
    public static void tearDownClass() {
        System.setOut(systemOut);
        System.setErr(systemErr);
    }

    @Test
    public void consoleLogger_givenLowPriorityLog_doesNotLog() {
        Logger logger = new ConsoleLogger(LogLevel.INFO);
        logger.debug("This is a test");
        assertEquals(0, stdout.toByteArray().length);
    }

    @Test
    public void consoleLogger_givenHighPriorityLog_logs() throws IOException {
        String message = "This is a test";
        Logger logger = new ConsoleLogger(LogLevel.INFO);
        logger.error(message);
        String result = new String(stderr.toByteArray());
        assertTrue(result.contains(message));
    }

    @Test
    public void consoleLogger_givenMessage_invokesDefaultFormatter() {
        Logger logger = new ConsoleLogger();
        logger.info("This is a test");
        String result = new String(stdout.toByteArray());
        assertTrue(result.contains("INFO"));
    }
}