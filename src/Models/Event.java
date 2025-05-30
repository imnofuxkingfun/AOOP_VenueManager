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
}
