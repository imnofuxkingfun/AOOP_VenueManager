import models.Menu;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        Menu menu =  Menu.getInstance();
        menu.start();
    }


}
