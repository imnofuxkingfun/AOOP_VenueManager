package Services;

import DatabaseConnection.MenuDB;
import Models.*;
import com.sun.source.tree.Tree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class VenueService extends MenuDB {

    private VenueService() {}

    private static final class SINGLETON_HOLDER {
        private static final VenueService INSTANCE = new VenueService();
    }

    public static VenueService getInstance() {
        AuditService.logAction("Singleton VenueService Instance Gotten");
        return SINGLETON_HOLDER.INSTANCE;
    }

    static AuditService auditService = new AuditService();

    public void displayVenue() throws SQLException {
        ResultSet venue =MenuDB.selectVenueStatement.executeQuery();
        venue.next();
        String name = venue.getString("name");
        String address = venue.getString("address");

        ResultSet nre = MenuDB.numberEventsStatement.executeQuery();
        nre.next();
        int nrEvents = nre.getInt(1);

        System.out.println("˗ˏˋ ★ ˎˊ˗ "+name+" ˗ˏˋ ★ ˎˊ˗" );
        System.out.println("Current address: "+address);
        System.out.println("Number of upcoming events: " + nrEvents);

        auditService.logAction("Displayed Venue Details");
    }

    public Venue getVenueEventData() throws SQLException{
        ResultSet venue =MenuDB.selectVenueStatement.executeQuery();
        venue.next();
        String name = venue.getString("name");
        String address = venue.getString("address");
        Integer id = 1;

        ResultSet eventList = MenuDB.getEventsStatement.executeQuery();
        Map<Date,Event> eventListForVenue = new HashMap<>();
        while (eventList.next()) {
            int eventId = eventList.getInt("id");
            String eventName = eventList.getString("name");
            String type = eventList.getString("type");
            String description = eventList.getString("description");
            Date date = eventList.getDate("date");

            //gasim si toti artistii sa ii punem in lista
            MenuDB.getEventArtistsStatement.setInt(1,eventId);
            ResultSet artistEventList = MenuDB.getEventArtistsStatement.executeQuery();
            List<Artist> eventArtistList = new ArrayList<>();
            while (artistEventList.next()) {
                int artistId = artistEventList.getInt("id");
                String artistName = artistEventList.getString("name");
                String artistEmail = artistEventList.getString("email");
                eventArtistList.add(new Artist(artistId,artistName,artistEmail,eventId));
            }

            //si la fel si la biletele vandute!
            MenuDB.getEventTicketStatement.setInt(1,eventId);
            ResultSet ticketEventList = MenuDB.getEventTicketStatement.executeQuery();
            List<Ticket> eventTicketList = new ArrayList<>();
            while (ticketEventList.next()) {
                int ticketId = ticketEventList.getInt("ticketId");
                int ticketSeatNumber = ticketEventList.getInt("seatNumber");
                String zoneTemp = ticketEventList.getString("zone");
                char ticketZone = zoneTemp.charAt(0);
                eventTicketList.add(new Ticket(ticketId,ticketSeatNumber,ticketZone));
            }
            Event tempEvent = new Event(eventId, eventName,type,description,date, eventTicketList, eventArtistList);
            eventListForVenue.put(tempEvent.getDate(),tempEvent);
        }
        //sortam map-ul
        Map<Date, Event> sortedEvents = new TreeMap<>(eventListForVenue);
        auditService.logAction("Loaded Venue Event Artist and EventTicket Data");
        return new Venue(id,name,address,sortedEvents);
    }

    public void displayVenueEvents(Venue v) throws SQLException{
        for(Map.Entry<Date, Event> e : v.getEventsByDate().entrySet()) {
            Event temp = e.getValue();
            System.out.println("[ID: " + temp.getId() + "] - [" + e.getKey() + "] - " + temp.getName());
        }
        auditService.logAction("Displayed Venue Events by date");
    }

    public void updateVenue() throws SQLException{
        System.out.println("Insert new venue name: (leave empty for no change)");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if (!name.isBlank()){
            MenuDB.updateVenueNameStatement.setString(1,name);
            MenuDB.updateVenueNameStatement.executeUpdate();
            auditService.logAction("Venue name updated");
        }
        System.out.println("Insert new venue address: (leave empty for no change)");
        String address = sc.nextLine();
        if (!address.isBlank()){
            MenuDB.updateVenueAddressStatement.setString(1,address);
            MenuDB.updateVenueAddressStatement.executeUpdate();
            auditService.logAction("Venue address updated");
        }

    }



}
