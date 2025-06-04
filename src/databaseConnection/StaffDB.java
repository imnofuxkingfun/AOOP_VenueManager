package databaseConnection;
import java.sql.PreparedStatement;

public class StaffDB extends ConnectionString{
    public static String rowCount = "select id from staff order by id desc limit 1";


    public static PreparedStatement rowCountStatement;

    static{
        try{
            rowCountStatement = c.prepareStatement(rowCount);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
