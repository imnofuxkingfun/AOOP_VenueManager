package DatabaseConnection;
import java.sql.PreparedStatement;

public class EventDB extends ConnectionString{
    public static String rowCount = "select id from event order by id desc limit 1";


    public static PreparedStatement rowCountStatement;

    static{
        try{
            rowCountStatement = c.prepareStatement(rowCount);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
