package Models;

public class Ticket {
    private  Integer id;
    private Integer seatNumber;
    private Character zone;
    private Double price;

    public Ticket(Integer id, Integer seatNumber, Character zone, Double ticketPrice) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.zone = zone;
        this.price = ticketPrice;
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

    public Double getTicketPrice() {
        return price;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.price = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + id +
                ", seatNumber=" + seatNumber +
                ", zone=" + zone +
                ", ticketPrice=" + price +
                '}';
    }
}
