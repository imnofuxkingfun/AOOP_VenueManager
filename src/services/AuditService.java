package services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private AuditService() {}

    private static final class SINGLETON_HOLDER {
        private static final AuditService INSTANCE = new AuditService();
    }
    public static AuditService getInstance() {
        return SINGLETON_HOLDER.INSTANCE;
    }

    private static final String file;
    static {
        file = "AOOP_VenueManager/src/AuditLog.csv";
    }

    public static void logAction(String content) {
        LocalDateTime timestamp = LocalDateTime.now();
        String time = timestamp.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss"));
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(content + ", " + time + "\n");
        } catch (IOException e) {
            System.err.println("Error printing to AuditLog.csv: " + e.getMessage());
        }
    }
}