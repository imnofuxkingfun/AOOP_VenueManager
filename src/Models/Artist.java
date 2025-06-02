package Models;

import DatabaseConnection.ArtistDB;

import java.sql.ResultSet;

public class Artist extends ArtistDB {
    private static Integer nextId;
    private Integer id;
    private String name;
    private String email;
    private Integer eventId;

    static {
        try{
            ResultSet rs = rowCountStatement.executeQuery();
            rs.next();
            nextId = rs.getInt(1);
            nextId = nextId + 1;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Artist(String name, String email, Integer eventId) {
        this.id = nextId;
        nextId = nextId + 1;
        this.name = name;
        this.email = email;
        this.eventId = eventId;
    }

    public Artist(Integer id, String name, String email, Integer eventId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.eventId = eventId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", eventId=" + eventId +
                '}';
    }
}
