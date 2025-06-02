package Services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
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