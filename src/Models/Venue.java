package Models;
import java.sql.ResultSet;
import java.util.*;
//clasa 'mama' dar o sa am doar un venue
public class Venue {
    private Integer id;
    private String name;
    private String address;
    //map cu de events dupa data lor - ordonata
    private Map<Date,Event> eventsByDate;


    public Venue(Integer id, String name, String address, Map<Date, Event> eventsByDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.eventsByDate = eventsByDate;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Date, Event> getEventsByDate() {
        return eventsByDate;
    }

    public void setEventsByDate(Map<Date, Event> eventsByDate) {
        this.eventsByDate = eventsByDate;
    }


}
