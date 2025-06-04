package services;

import databaseConnection.MenuDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TicketService extends MenuDB{

    private TicketService(){}

    private static final class SINGLETON_HOLDER {
        private static final TicketService INSTANCE = new TicketService();
    }

    public static TicketService getInstance() {
        AuditService.logAction("Singleton TicketService Instance Gotten");
        return SINGLETON_HOLDER.INSTANCE;
    }

    static AuditService auditService = AuditService.getInstance();

    public double getPrice(char zone) {
        if (zone == 'V')
            return 70;
        else if (zone == 'A')
            return 50;
        else if (zone == 'B' | zone == 'C')
            return 35;
        else if (zone == 'D')
            return 25;
        return 0;
    }

    public void sellTicket(int eventId) throws SQLException {
        //cerem zona si gasim daca mai sunt bileet in zona aia si un pret
        System.out.println("Insert the zone of the ticket you wish to sell (V,A,B,C or D): ");
        Scanner sc = new Scanner(System.in);
        String zone = sc.nextLine();
        if (zone.equalsIgnoreCase("V") || zone.equalsIgnoreCase("A") || zone.equalsIgnoreCase("B") || zone.equalsIgnoreCase("C") || zone.equalsIgnoreCase("D")) {
            boolean okNumber = false;
            int ticketNr = 0;
            while(!okNumber){
                System.out.println("Insert how many tickets you wish to sell: ");
                String ticketString = sc.nextLine();

                try {
                    ticketNr = Integer.parseInt(ticketString);
                    okNumber = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number");
                }
            }

            //daca mai sunt bilete
            MenuDB.countZoneTicketStatement.setString(1, zone);
            MenuDB.countZoneTicketStatement.setInt(2, eventId);
            ResultSet rs = MenuDB.countZoneTicketStatement.executeQuery();
            rs.next();
            int currentZoneTicketNumber = rs.getInt(1);

            MenuDB.maxZoneTicketStatement.setString(1, zone);
            ResultSet rs2 = MenuDB.maxZoneTicketStatement.executeQuery();
            rs2.next();
            int maxZoneTicketNumber = rs2.getInt(1);

            int seatNumber = 0, ticketId = 0;

            if (currentZoneTicketNumber  >= maxZoneTicketNumber) {
                System.out.println("No More Tickets in zone " + zone + " for this event!");
            }
            else if(currentZoneTicketNumber + ticketNr > maxZoneTicketNumber){
                System.out.println("Not enough Tickets in zone "+zone+" for this event!");
                System.out.println("Remaining tickets in zone "+zone+" : "+ (maxZoneTicketNumber - currentZoneTicketNumber));
            }
            else{
                while(ticketNr > 0) {
                    if (currentZoneTicketNumber == 0) {
                        MenuDB.firstZoneTicketStatement.setString(1, zone);
                        ResultSet rs3 = MenuDB.firstZoneTicketStatement.executeQuery();
                        rs3.next();
                        ticketId = rs3.getInt("id");
                        seatNumber = rs3.getInt("seatNumber");
                        currentZoneTicketNumber++;
                    } else {
                        MenuDB.findNextZoneTicketStatement.setString(1, zone);
                        MenuDB.findNextZoneTicketStatement.setInt(2, eventId);
                        ResultSet rs3 = MenuDB.findNextZoneTicketStatement.executeQuery();
                        rs3.next();
                        ticketId = rs3.getInt("id") + 1;
                        seatNumber = rs3.getInt("seatNumber") + 1;
                    }


                    MenuDB.insertEventTicketStatement.setInt(1, eventId);
                    MenuDB.insertEventTicketStatement.setInt(2, ticketId);
                    MenuDB.insertEventTicketStatement.execute();
                    System.out.println("Sold ticket " + zone + seatNumber + " with id " + ticketId + " for " + getPrice(zone.toCharArray()[0]) + "$");
                    auditService.logAction("Sold ticket " + zone + seatNumber + " with id " + ticketId + " for event with id " + eventId);
                    ticketNr--;
                }
            }

        }
        else{
            System.out.println("Please enter a valid zone code");
        }
    }
}
