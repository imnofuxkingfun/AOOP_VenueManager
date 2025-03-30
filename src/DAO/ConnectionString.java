package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionString {

    protected static String connectionString = "jdbc:mysql://localhost:3306/recordlabelschema";
    protected static String userName = "root";
    protected static String password = "record44LABEL";
    protected static Connection c;

    static {
        try {
            c = DriverManager.getConnection(connectionString,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
