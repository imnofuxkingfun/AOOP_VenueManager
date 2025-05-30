package Models;

import java.util.Date;
import java.util.*;

public class Event {
    private Integer id;
    private String name;
    private String type; //pop rock punk clsical musical etc
    private String description; //gen ca pe site uri
    private Date date;
    //lista cu bilete vandute
    private List<Ticket> soldTickets;
    //lista de artisti
    private List<Artist> artists;

    public Event(Integer id, String name, String type, String description, Date date) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public Event(Integer id, String name, String type, String description, Date date, List<Ticket> soldTickets, List<Artist> artists) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.date = date;
        this.soldTickets = soldTickets;
        this.artists = artists;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Ticket> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(List<Ticket> soldTickets) {
        this.soldTickets = soldTickets;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public void EventDetails() {
        System.out.println( "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'');
    }
}
