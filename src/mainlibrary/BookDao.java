package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDao {

    // Logger to log exceptions and other info
    private static final Logger LOGGER = Logger.getLogger(BookDao.class.getName());

    public static int save(String callno, String name, String author, String publisher, int quantity) {
        int status = 0;
        String sql = "INSERT INTO books(callno, name, author, publisher, quantity) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = DB.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, callno);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setInt(5, quantity);

            status = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving book", e);
        }
        return status;
    }

    public static boolean publisherValidate(String publisher) {
        boolean status = false;
        String sql = "SELECT * FROM Publisher WHERE PublisherName = ?";
        try (Connection con = DB.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, publisher);

            try (ResultSet rs = ps.executeQuery()) {
                status = rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error validating publisher", e);
        }
        return status;
    }

    public static int addPublisher(String publisher) {
        int status = 0;
        String sql = "INSERT INTO Publisher(PublisherName) VALUES(?)";
        try (Connection con = DB.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, publisher);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding publisher", e);
        }
        return status;
    }

    public static int saveBook(String bookN, String authorN, String publisherN, String shelfN, String rowN, String genreN) {
        int status = 0;
        String sql = "INSERT INTO Books(BookName, Author, Genre, Publisher, Shelf, Row) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = DB.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bookN);
            ps.setString(2, authorN);
            ps.setString(3, genreN);
            ps.setString(4, publisherN);
            ps.setString(5, shelfN);
            ps.setString(6, rowN);

            status = ps.executeUpdate();
        } catch (SQLException e) {
    //        LOGGER.log(Level.SEVERE, "Error saving book", e);
        }
        return status;
    }

    public static int delete(int bookID) {
        int status = 0;
        String sql = "DELETE FROM Books WHERE BookID = ?";
        try (Connection con = DB.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookID);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting book", e);
        }
        return status;
    }
}
