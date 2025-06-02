package DatabaseConnection;

import java.sql.PreparedStatement;

public class MenuDB extends ConnectionString {
    //venue statements
    public static String selectVenue ="select name,address from venue";
    public static String updateVenueName = "update venue set name=? where id=1";
    public static String updateVenueAddress = "update venue set address=? where id=1";

    //event statements
    public static String numberEvents ="select count(*) from event";
    public static String getEvents = "select * from event";
    public static String getEventArtists = "select * from artist where eventId = ?";
    public static String getEventTickets = "select * from ticket t join eventTicket et on t.id = et.ticketId where eventId = ?";
    public static String insertEvent = "insert into event values(?,?,?,?,?)";
    public static String validEventId = "select count(*) from event where id = ?";
    public static String selectEvent = "select e.id,e.name,e.type,e.description,e.date,count(distinct (a.id)) as artistNumber, count(distinct (t.ticketId)) as ticketNumber from event e left outer join artist a on e.id = a.eventId left outer join eventticket t on e.id = t.eventId where e.id = ?";
    public static String deleteEvent = "delete from event where id = ?";
    public static String updateEventName = "update event set name=? where id=?";
    public static String updateEventType = "update event set type=? where id=?";
    public static String updateEventDescription = "update event set description=? where id=?";
    public static String updateEventDate = "update event set date=? where id=?";

    //ticket statements
    public static String countZoneTicket = "select count(*) from eventTicket et right outer join ticket t on et.ticketId = t.id where zone = ? and et.eventId = ?";
    public static String findNextZoneTicket = "select id, seatNumber from ticket t left outer join eventTicket et on et.ticketId = t.id where zone = ? and eventId = ? order by seatNumber desc limit 1";
    public static String maxZoneTicket = "select seatNumber from ticket t where zone = ? order by seatNumber desc limit 1";
    public static String firstZoneTicket = "select id,seatNumber from ticket t  where zone = ? and seatNumber = 1";
    public static String insertEventTicket = "insert into eventTicket values(?,?)";

    //artist statements
    public static String validArtistId = "select count(*) from artist where id = ? and eventId=?";
    public static String deleteArtist = "delete from artist where id = ?";
    public static String allEventArtists = "select id, name, email from artist where eventId = ?";
    public static String updateArtistName = "update artist set name=? where id=?";
    public static String updateArtistEmail = "update artist set email=? where id=?";
    public static String insertArtist = "insert into artist values(?,?,?,?)";

    //staff statements
    public static String getStaff = "select * from staff s left outer join staffmanager sm on s.id = sm.id";
    public static String findManagerName = "select ifnull((select name from staff where id = ?),'is boss') as name;";
    public static String checkManagerId = "select count(*) from staffmanager where id = ?";
    public static String checkStaffId = "select count(*) from staff where id = ?";
    public static String insertStaff = "insert into staff values(?,?,?,?,?)";
    public static String deleteStaff = "delete from staff where id = ?";
    public static String updateStaffName = "update staff set name=? where id=?";
    public static String updateStaffDepartment = "update staff set department=? where id=?";
    public static String updateStaffSalary = "update staff set salary=? where id=?";
    public static String updateStaffManagerId = "update staff set managerId=? where id=?";
    public static String updateManagerAuthorityLevel = "update staffmanager set authorityLevel=? where id=?";
    public static String addManager = "insert into staffmanager values(?,?)";
    public static String deleteManager = "delete from staffmanager where id = ?";

    //prepared statements
    //venue
    public static PreparedStatement selectVenueStatement;
    public static PreparedStatement updateVenueNameStatement;
    public static PreparedStatement updateVenueAddressStatement;

    //events
    public static PreparedStatement numberEventsStatement;
    public static PreparedStatement getEventsStatement;
    public static PreparedStatement getEventArtistsStatement;
    public static PreparedStatement getEventTicketStatement;
    public static PreparedStatement insertEventStatement;
    public static PreparedStatement validEventIdStatement;
    public static PreparedStatement selectEventStatement;
    public static PreparedStatement deleteEventStatement;
    public static PreparedStatement updateEventNameStatement;
    public static PreparedStatement updateEventTypeStatement;
    public static PreparedStatement updateEventDescriptionStatement;
    public static PreparedStatement updateEventDateStatement;

    //ticket
    public static PreparedStatement countZoneTicketStatement;
    public static PreparedStatement findNextZoneTicketStatement;
    public static PreparedStatement maxZoneTicketStatement;
    public static PreparedStatement firstZoneTicketStatement;
    public static PreparedStatement insertEventTicketStatement;

    //artist
    public static PreparedStatement validArtistIdStatement;
    public static PreparedStatement deleteArtistStatement;
    public static PreparedStatement allEventArtistsStatement;
    public static PreparedStatement updateArtistNameStatement;
    public static PreparedStatement updateArtistEmailStatement;
    public static PreparedStatement insertArtistStatement;

    //staff
    public static PreparedStatement getStaffStatement;
    public static PreparedStatement findManagerNameStatement;
    public static PreparedStatement checkStaffIdStatement;
    public static PreparedStatement checkManagerIdStatement;
    public static PreparedStatement insertStaffStatement;
    public static PreparedStatement deleteStaffStatement;
    public static PreparedStatement updateStaffNameStatement;
    public static PreparedStatement updateStaffDepartmentStatement;
    public static PreparedStatement updateStaffSalaryStatement;
    public static PreparedStatement updateStaffManagerIdStatement;
    public static PreparedStatement updateManagerAuthorityLevelStatement;
    public static PreparedStatement addManagerStatement;
    public static PreparedStatement deleteManagerStatement;



    static{
        try{
            selectVenueStatement = c.prepareStatement(selectVenue);
            updateVenueNameStatement = c.prepareStatement(updateVenueName);
            updateVenueAddressStatement = c.prepareStatement(updateVenueAddress);
            numberEventsStatement = c.prepareStatement(numberEvents);
            getEventsStatement = c.prepareStatement(getEvents);
            getEventArtistsStatement = c.prepareStatement(getEventArtists);
            getEventTicketStatement = c.prepareStatement(getEventTickets);
            insertEventStatement = c.prepareStatement(insertEvent);
            validEventIdStatement = c.prepareStatement(validEventId);
            selectEventStatement = c.prepareStatement(selectEvent);
            deleteEventStatement = c.prepareStatement(deleteEvent);
            updateEventNameStatement = c.prepareStatement(updateEventName);
            updateEventTypeStatement = c.prepareStatement(updateEventType);
            updateEventDescriptionStatement = c.prepareStatement(updateEventDescription);
            updateEventDateStatement = c.prepareStatement(updateEventDate);
            countZoneTicketStatement = c.prepareStatement(countZoneTicket);
            findNextZoneTicketStatement = c.prepareStatement(findNextZoneTicket);
            maxZoneTicketStatement = c.prepareStatement(maxZoneTicket);
            firstZoneTicketStatement = c.prepareStatement(firstZoneTicket);
            insertEventTicketStatement = c.prepareStatement(insertEventTicket);
            validArtistIdStatement = c.prepareStatement(validArtistId);
            deleteArtistStatement = c.prepareStatement(deleteArtist);
            allEventArtistsStatement = c.prepareStatement(allEventArtists);
            updateArtistNameStatement = c.prepareStatement(updateArtistName);
            updateArtistEmailStatement = c.prepareStatement(updateArtistEmail);
            insertArtistStatement = c.prepareStatement(insertArtist);
            getStaffStatement = c.prepareStatement(getStaff);
            findManagerNameStatement = c.prepareStatement(findManagerName);
            checkStaffIdStatement = c.prepareStatement(checkStaffId);
            checkManagerIdStatement = c.prepareStatement(checkManagerId);
            insertStaffStatement = c.prepareStatement(insertStaff);
            deleteStaffStatement = c.prepareStatement(deleteStaff);
            updateStaffNameStatement = c.prepareStatement(updateStaffName);
            updateStaffDepartmentStatement = c.prepareStatement(updateStaffDepartment);
            updateStaffSalaryStatement = c.prepareStatement(updateStaffSalary);
            updateStaffManagerIdStatement = c.prepareStatement(updateStaffManagerId);
            updateManagerAuthorityLevelStatement = c.prepareStatement(updateManagerAuthorityLevel);
            addManagerStatement = c.prepareStatement(addManager);
            deleteManagerStatement = c.prepareStatement(deleteManager);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
