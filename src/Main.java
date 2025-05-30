import Models.Menu;
import DatabaseConnection.DatabaseInitialization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Menu menu =  Menu.getInstance();
        menu.start();
    }


}
