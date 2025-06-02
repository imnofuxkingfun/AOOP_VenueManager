package Models;

import DatabaseConnection.MenuDB;
import Services.*;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class Menu extends MenuDB{

    //cum am singleton le fac aici decat in constructor
    private static final AuditService auditService = new AuditService();
    private static final VenueService venueService = VenueService.getInstance();
    private static final EventService eventService = EventService.getInstance();
    private static final TicketService ticketService = TicketService.getInstance();
    private static final ArtistService artistService = ArtistService.getInstance();
    private static final StaffService staffService = StaffService.getInstance();

    private Menu() {}
    private final class SINGLETON_HOLDER{
        private static final Menu INSTANCE = new Menu();
    }
    public static Menu getInstance() {
        Menu instance = SINGLETON_HOLDER.INSTANCE;
        auditService.logAction("\nSingleton Menu Instance Gotten");
        return instance;
    }

    public void start() throws SQLException, ParseException {
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
                case "2":
                    displayStaff();
                    break;
                case "3":
                    updateVenueDetails();
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

    public void displayEvents() throws SQLException, ParseException {
        Venue v = venueService.getVenueEventData();
        System.out.println("--------UPCOMING EVENTS ORDERED BY DATE--------");
        venueService.displayVenueEvents(v);
        System.out.println("1.View a specific event");
        System.out.println("2.Insert a new event");
        System.out.println("3.Exit");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        auditService.logAction("Displayed Events Menu");
        switch (option){
            case "1":
                System.out.println("Enter event id: ");
                int id = sc.nextInt();
                //check daca id ul exista in bd
                MenuDB.validEventIdStatement.setInt(1, id);
                ResultSet rs = MenuDB.validEventIdStatement.executeQuery();
                rs.next(); //aici fac counr ca sa nu mai schimb ce am scris + sa nu imi poata da null
                if (rs.getInt(1) == 0){
                    System.out.println("Invalid event id. Try again\n");
                }
                else{
                    eventMenu(id);
                }
                displayEvents();
            case "2":
                eventService.addEvent();
                displayEvents();
            case "3":
                start();
            default:
                System.out.println("Invalid option");
                displayEvents();

        }
    }

    public void eventMenu(int id) throws SQLException, ParseException {
        System.out.println("\n------EVENT MENU------");
        eventService.displayEvent(id);
        System.out.println();
        System.out.println("1.View artist(s)");
        System.out.println("2.Sell a ticket");
        System.out.println("3.Update event details");
        System.out.println("4.Delete event");
        System.out.println("5.Exit");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option){
            case "1":
                artistsMenu(id);
                eventMenu(id);
            case "2":
                ticketService.sellTicket(id);
                eventMenu(id);
            case "3":
                eventService.updateEvent(id);
                eventMenu(id);
            case "4":
                if(eventService.deleteEvent(id))
                    displayEvents();
                else
                    eventMenu(id);
            case "5":
                displayEvents();
            default:
                System.out.println("Invalid option");
                displayEvents();

        }
    }

    public void artistsMenu(int eventId) throws SQLException, ParseException {
        System.out.println("\n--------------");
        artistService.displayEventArtists(eventId);
        System.out.println("1.Add a new artist to this event");
        System.out.println("2.Edit an artist's details");
        System.out.println("3.Remove a artist from this event");
        System.out.println("4.Exit");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option){
            case "1":
                artistService.addArtist(eventId);
                artistsMenu(eventId);
            case "2":
                System.out.println("Enter artist id: ");
                int artistId = sc.nextInt();
                MenuDB.validArtistIdStatement.setInt(1, artistId);
                MenuDB.validArtistIdStatement.setInt(2, eventId);
                ResultSet rs = MenuDB.validArtistIdStatement.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0){
                    System.out.println("Invalid artist id. Try again\n");
                }else{
                    artistService.editArtist(artistId);
                }
               artistsMenu(eventId);
            case "3":
                System.out.println("Enter artist id: ");
                int artistId2 = sc.nextInt();
                MenuDB.validArtistIdStatement.setInt(1, artistId2);
                MenuDB.validArtistIdStatement.setInt(2, eventId);
                ResultSet rs2 = MenuDB.validArtistIdStatement.executeQuery();
                rs2.next();
                if (rs2.getInt(1) == 0){
                    System.out.println("Invalid artist id. Try again\n");
                }else{
                    artistService.deleteArtist(artistId2);
                }
                artistsMenu(eventId);
            case "4":
                eventMenu(eventId);
            default:
                System.out.println("Invalid option");
                artistsMenu(eventId);
        }
    }

    public void displayStaff() throws SQLException, ParseException {
        List<Staff> staffList = staffService.getVenueStaffData();
        staffService.displayStaffDetails(staffList);
        System.out.println("\n1.Add a new employee");
        System.out.println("2.Edit an employee's details");
        System.out.println("3.Remove a employee");
        System.out.println("4.Exit");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option){
            case "1":
                staffService.addStaff();
                displayStaff();
            case "2":
                System.out.println("Enter staff id: ");
                int staffId = sc.nextInt();
                //checker daca e valid
                MenuDB.checkStaffIdStatement.setInt(1, staffId);
                ResultSet rs = MenuDB.checkStaffIdStatement.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0){
                    System.out.println("Invalid staff id. Try again\n");
                    displayStaff();
                }else { //check intre staff si manager for different menus
                    MenuDB.checkManagerIdStatement.setInt(1, staffId);
                    ResultSet rs3 = MenuDB.checkManagerIdStatement.executeQuery();
                    rs3.next();
                    if (rs3.getInt(1) == 0) {
                        editStaffMenu(staffId);
                        displayStaff();
                    }
                    else {
                        editManagerMenu(staffId);
                        displayStaff();
                    }
                }
            case "3":
                System.out.println("Enter staff id: ");
                int staffId2 = sc.nextInt();
                //checker daca e valid
                MenuDB.checkStaffIdStatement.setInt(1, staffId2);
                ResultSet rs2 = MenuDB.checkStaffIdStatement.executeQuery();
                rs2.next();
                if (rs2.getInt(1) == 0){
                    System.out.println("Invalid staff id. Try again\n");
                    displayStaff();
                }else {
                    staffService.deleteStaff(staffId2);
                    displayStaff();
                }
            case "4":
                start();
            default:
                System.out.println("Invalid option");
                displayStaff();
        }
    }

    public void editStaffMenu(int id) throws SQLException {
        System.out.println("--Editing employee with id:"+id);
        System.out.println("1.Edit employee details");
        System.out.println("2.Promote to manager");
        System.out.println("3.Exit");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option){
            case "1":
                staffService.editStaff(id);
                break;
            case "2":
                staffService.promoteStaff(id);
                break;
            case "3":
                break;
            default:
                System.out.println("Invalid option");
                editStaffMenu(id);
        }

    }

    public void editManagerMenu(int id) throws SQLException {
        System.out.println("--Editing manager with id:"+id);
        System.out.println("1.Edit manager details");
        System.out.println("2.Demote from manager");
        System.out.println("3.Exit");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option){
            case "1":
                staffService.editManager(id);
                break;
            case "2":
                staffService.demoteManager(id);
                break;
            case "3":
                break;
            default:
                System.out.println("Invalid option");
                editManagerMenu(id);
        }

    }

    public void updateVenueDetails() throws SQLException{

        System.out.println("--------Update Venue Details--------");
        venueService.updateVenue();
    }

}
