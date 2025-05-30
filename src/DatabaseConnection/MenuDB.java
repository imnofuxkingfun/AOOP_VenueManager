package DatabaseConnection;

import java.sql.PreparedStatement;

public class MenuDB extends ConnectionString {
    public static String selectVenue ="select name,address from venue";
    public static String numberEvents ="select count(*) from event";
    public static String getEvents = "select * from event";
    public static String getEventArtists = "select * from artist where eventId = ?";
    public static String getEventTickets = "select * from ticket t join eventTicket et on t.id = et.ticketId where eventId = ?";

    public static PreparedStatement selectVenueStatement;
    public static PreparedStatement numberEventsStatement;
    public static PreparedStatement getEventsStatement;
    public static PreparedStatement getEventArtistsStatement;
    public static PreparedStatement getEventTicketStatement;

    static{
        try{
            selectVenueStatement = c.prepareStatement(selectVenue);
            numberEventsStatement = c.prepareStatement(numberEvents);
            getEventsStatement = c.prepareStatement(getEvents);
            getEventArtistsStatement = c.prepareStatement(getEventArtists);
            getEventTicketStatement = c.prepareStatement(getEventTickets);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
