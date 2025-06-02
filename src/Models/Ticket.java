package Models;

public class Ticket {
    private  Integer id;
    private Integer seatNumber;
    private Character zone;

    //teoretic nu am intetia sa creez obiecte noi de tip tickets

    public Ticket(Integer id, Integer seatNumber, Character zone) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.zone = zone;
    }

    public Integer getTicketID() {
        return id;
    }

    public void setTicketID(Integer ticketID) {
        this.id = ticketID;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Character getZone() {
        return zone;
    }

    public void setZone(Character zone) {
        this.zone = zone;
    }


}
