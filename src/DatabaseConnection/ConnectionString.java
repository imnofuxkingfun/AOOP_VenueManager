package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Services.AuditService;

public class ConnectionString {
    static AuditService auditService = AuditService.getInstance();
    protected static String connectionString = "jdbc:mysql://localhost:3306/venuemanager"; //recordlabelschema";
    protected static String userName = "root";
    protected static String password = "record44LABEL";
    protected static Connection c;

    static {
        try {
            c = DriverManager.getConnection(connectionString,userName,password);
            if (c != null) {
                auditService.logAction("Connected to database");
            }
            else
                auditService.logAction("Failed to connect to database");
        } catch (SQLException e) {
            auditService.logAction("Connection Test Fail");
            System.out.println(e.getMessage());
        }
    }
}
