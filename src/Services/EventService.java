package Services;

import DatabaseConnection.MenuDB;
import Models.*;
import com.sun.source.tree.Tree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import DatabaseConnection.MenuDB;

public class EventService extends MenuDB {

    private EventService() {}

    private static final class SINGLETON_HOLDER {
        private static final EventService INSTANCE = new EventService();
    }

    public static EventService getInstance() {
        auditService.logAction("Singleton EventService Instance Gotten");
        return SINGLETON_HOLDER.INSTANCE;
    }

    static AuditService auditService = new AuditService();

    public void displayEvent(int id) throws SQLException{
        //nr of artists and tickets sold
        System.out.println("Event id: " + id);
        MenuDB.selectEventStatement.setInt(1, id);
        ResultSet rs = MenuDB.selectEventStatement.executeQuery();
        rs.next();
        System.out.println("Event Name: " + rs.getString("name"));
        System.out.println("Event Type: " + rs.getString("type"));
        System.out.println("Event Description: " + rs.getString("description"));
        System.out.println("Event Date: " + rs.getDate("date"));
        System.out.println("Number of Artists: " + rs.getInt("artistNumber"));
        System.out.println("Number of tickers sold: " + rs.getInt("ticketNumber"));

        auditService.logAction("Displayed event details for event with id: " + id);
    }

    public void updateEvent(int id) throws SQLException{
        System.out.println("Insert new event name: (leave empty for no change)");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if (!name.isBlank()){
            MenuDB.updateEventNameStatement.setString(1,name);
            MenuDB.updateEventNameStatement.setInt(2,id);
            MenuDB.updateEventNameStatement.executeUpdate();
            auditService.logAction("Event with id: " + id +" name updated");
        }

        System.out.println("Insert new event type: (leave empty for no change)");
        String type = sc.nextLine();
        if (!type.isBlank()) {
            MenuDB.updateEventTypeStatement.setString(1, type);
            MenuDB.updateEventTypeStatement.setInt(2, id);
            MenuDB.updateEventTypeStatement.executeUpdate();
            auditService.logAction("Event with id: " + id + " type updated");
        }

        System.out.println("Insert new event description: (leave empty for no change)");
        String description = sc.nextLine();
        if (!description.isBlank()) {
            MenuDB.updateEventDescriptionStatement.setString(1, description);
            MenuDB.updateEventDescriptionStatement.setInt(2, id);
            MenuDB.updateEventDescriptionStatement.executeUpdate();
            auditService.logAction("Event with id: " + id + " description updated");
        }


        boolean dateok = false;
        while (!dateok) {
            try {
                System.out.println("Insert new event date (yyyy-MM-dd): (leave empty for no change)");
                String dateString = sc.nextLine();
                if (!dateString.isBlank()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    formatter.setLenient(false); //ptr date validation
                    java.util.Date parsedDate = formatter.parse(dateString);
                    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

                    MenuDB.updateEventDateStatement.setDate(1, sqlDate);
                    MenuDB.updateEventDateStatement.setInt(2, id);
                    MenuDB.updateEventDateStatement.executeUpdate();
                    auditService.logAction("Event with id: " + id + " date updated");
                }
                dateok = true;
            } catch (ParseException e) {
                System.out.println("Date MUST be a valid date in the format yyyy-MM-dd. Try again");
            }
        }

    }

    public boolean deleteEvent(int id) throws SQLException{
        System.out.println("Are you sure you want to delete the event with id: " + id  + " ? Type \"yes\" to continue...");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if(answer.equalsIgnoreCase("yes")){
            MenuDB.deleteEventStatement.setInt(1, id);
            deleteEventStatement.execute();
            auditService.logAction("Deleted event with id: " + id);
            return true;
        }
        return false;
    }

    public void addEvent() throws SQLException, ParseException {
        System.out.println("\n--Adding new event--");
        String name, type, description, dateString;
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false); //ptr date validation

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter event name: ");
        name = sc.nextLine();
        System.out.println("Enter event type: ");
        type = sc.nextLine();
        System.out.println("Enter event description: ");
        description = sc.nextLine();
        java.sql.Date sqlDate = null;

        boolean dateok = false;
        while (!dateok){
            try {
                System.out.println("Enter event date (yyyy-MM-dd): ");
                dateString = sc.nextLine();
                date = formatter.parse(dateString);
                sqlDate = new java.sql.Date(date.getTime());
                dateok = true;
            } catch (Exception e) {
                System.out.println("Date MUST be a valid date in the format yyyy-MM-dd. Try again");
            }
        }
        System.out.println();
        Event event = new Event(name,type,description,date); //fac obiect ca sa ii faca si id-ul
        //acum bagam in baza de date
        //"(1, 'TWRP Concert', 'Funk Rock', 'The Longest Weekend European DLC Tour', DATE('2026-02-07'));\n";
        MenuDB.insertEventStatement.setInt(1,event.getId());
        MenuDB.insertEventStatement.setString(2,event.getName());
        MenuDB.insertEventStatement.setString(3,event.getType());
        MenuDB.insertEventStatement.setString(4,event.getDescription());
        MenuDB.insertEventStatement.setDate(5, sqlDate );
        insertEventStatement.execute();
        auditService.logAction("New Event added");
    }


}