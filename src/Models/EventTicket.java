package Models;

public class EventTicket {
    private Integer eventId;
    private Integer ticketId;

    public EventTicket(Integer eventId, Integer ticketId) {
        this.eventId = eventId;
        this.ticketId = ticketId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
