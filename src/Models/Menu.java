package Models;

import DatabaseConnection.MenuDB;
import Services.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

public class Menu extends MenuDB{

    //cum am singleton le fac aici decat in constructor
    private static AuditService auditService = new AuditService();
    private VenueService venueService = new VenueService();


    private Menu() {}
    private final class SINGLETON_HOLDER{
        private static final Menu INSTANCE = new Menu();
    }
    public static Menu getInstance() {
        Menu instance = SINGLETON_HOLDER.INSTANCE;
        auditService.logAction("Singleton Menu Instance Gotten");
        return instance;
    }

    public void start() throws SQLException {
        while (true){
            System.out.println();
            System.out.println(" Welcome to your Concert Venue Database Manager!");
            System.out.println("-----------------------------------------------");
            venueService.displayVenue();
            System.out.println("-----------------------------------------------");
            System.out.println();
            System.out.println("What would you like to do? (input the option number)");
            System.out.println("1.View events by date");
            System.out.println("2.View staff");
            System.out.println("3.Update venue details");
            System.out.println("4.Exit");


            Scanner sc = new Scanner(System.in);
            String option = sc.nextLine();
            switch (option){
                case "1":
                    displayEvents();
                    break;

                case "4":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

    }

    public void displayEvents() throws SQLException{
        Venue v = venueService.getVenueEventData();
        venueService.displayVenueEvents(v);
    }

}
