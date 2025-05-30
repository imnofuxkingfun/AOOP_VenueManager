import Services.DataService;
import Database.DatabaseInitialization;

public class Main {
    DataService dataService =  DataService.getInstance();

    public static void main(String[] args) {
        DatabaseInitialization dbCon = new DatabaseInitialization(); //bd

    }



}
