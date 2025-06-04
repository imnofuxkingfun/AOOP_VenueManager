package Services;

import DatabaseConnection.MenuDB;
import Models.*;
import com.sun.source.tree.Tree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class ArtistService extends MenuDB {
    private ArtistService() {}
    private final class SINGLETON_HOLDER{
        private static final ArtistService INSTANCE = new ArtistService();
    }
    public static ArtistService getInstance() {
        ArtistService instance = ArtistService.SINGLETON_HOLDER.INSTANCE;
        auditService.logAction("Singleton ArtistService Instance Gotten");
        return instance;
    }

    private static AuditService auditService = AuditService.getInstance();

    public void displayEventArtists(int eventId) throws SQLException {
        MenuDB.allEventArtistsStatement.setInt(1,eventId);
        ResultSet rs = MenuDB.allEventArtistsStatement.executeQuery();
        while (rs.next()) {
            System.out.println("[ID: " + rs.getInt("id") + "] " + rs.getString("name") + " - " + rs.getString("email"));
        }
        auditService.logAction("Displayed artists for event with id: " + eventId);
    }

    public void addArtist(int eventId) throws SQLException {
        System.out.println("\n----Adding new artist to this event----");
        String name, email;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter artist name: ");
        name = sc.nextLine();
        System.out.println("Enter artist email: ");
        email = sc.nextLine();
        System.out.println();
        Artist artist = new Artist(name,email,eventId);
        MenuDB.insertArtistStatement.setInt(1,artist.getId());
        MenuDB.insertArtistStatement.setString(2,artist.getName());
        MenuDB.insertArtistStatement.setString(3,artist.getEmail());
        MenuDB.insertArtistStatement.setInt(4,eventId);
        MenuDB.insertArtistStatement.execute();
        auditService.logAction("Added artist with id " + artist.getId() + "to the event with id "+ eventId);
    }

    public void editArtist(int id) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert new artist name: (leave empty for no change)");
        String artistName = sc.nextLine();
        if (!artistName.isBlank()) {
            MenuDB.updateArtistNameStatement.setString(1, artistName);
            MenuDB.updateArtistNameStatement.setInt(2, id);
            MenuDB.updateArtistNameStatement.executeUpdate();
            auditService.logAction("Artist with id: " + id + " name updated");
        }
        System.out.println("Insert new artist email: (leave empty for no change)");
        String artistEmail = sc.nextLine();
        if (!artistEmail.isBlank()) {
            MenuDB.updateArtistEmailStatement.setString(1, artistEmail);
            MenuDB.updateArtistEmailStatement.setInt(2, id); // id of the artist
            MenuDB.updateArtistEmailStatement.executeUpdate();
            auditService.logAction("Artist with id: " + id + " email updated");
        }

    }

    public void deleteArtist(int id) throws SQLException{
        System.out.println("Are you sure you want to delete the artist with id: " + id  + " ? Type \"yes\" to continue...");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if(answer.equalsIgnoreCase("yes")){
            MenuDB.deleteArtistStatement.setInt(1, id);
            deleteArtistStatement.execute();
            auditService.logAction("Deleted artist with id: " + id);
        }
    }

}
