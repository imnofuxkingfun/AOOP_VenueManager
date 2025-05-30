package Models;
import java.util.*;
//clasa 'mama' dar o sa am doar un venue
public class Venue {
    private Integer id;
    private String name;
    //map cu de events dupa data lor - ordonata
    private Map<Date,Event> eventsByDate;
}
