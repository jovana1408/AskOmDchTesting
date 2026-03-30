package com.askomdch.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {

    private static PrintWriter writer;
    private static final String FILE_NAME = "test-report.txt";

    static {
        try {
            writer = new PrintWriter(new FileWriter(FILE_NAME, false));
            writer.println("===========================================");
            writer.println("   IZVESTAJ TESTIRANJA - ASKOMDCH.COM     ");
            writer.println("===========================================");
            writer.println("Datum: " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
            writer.println("===========================================");
            writer.println();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String testName, String description, boolean passed) {
        String status = passed ? "PROSAO" : "PAO";
        writer.println("------------------------------------------");
        writer.println("Test:    " + testName);
        writer.println("Opis:    " + description);
        writer.println("Status:  " + status);
        writer.println("Vreme:   " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        writer.println();
        writer.flush();
    }

    public static void logInfo(String message) {
        writer.println("[INFO] " + message);
        writer.flush();
    }

    public static void close() {
        writer.println("===========================================");
        writer.println("         KRAJ IZVESTAJA                    ");
        writer.println("===========================================");
        writer.flush();
        writer.close();
    }
}