package com.library.dao;

import com.library.models.Librarian;
import com.library.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDAO {

    // Add new librarian
    public boolean addLibrarian(Librarian librarian) {
        String query = "INSERT INTO librarians (name, username, password, email, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, librarian.getName());
            stmt.setString(2, librarian.getUsername());
            stmt.setString(3, librarian.getPassword());
            stmt.setString(4, librarian.getEmail());
            stmt.setString(5, librarian.getRole());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding librarian: " + e.getMessage());
            return false;
        }
    }

    // Update librarian
    public boolean updateLibrarian(Librarian librarian) {
        String query = "UPDATE librarians SET name = ?, username = ?, password = ?, email = ?, role = ? "
                + "WHERE librarian_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, librarian.getName());
            stmt.setString(2, librarian.getUsername());
            stmt.setString(3, librarian.getPassword());
            stmt.setString(4, librarian.getEmail());
            stmt.setString(5, librarian.getRole());
            stmt.setInt(6, librarian.getLibrarianId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating librarian: " + e.getMessage());
            return false;
        }
    }

    // Delete librarian
    public boolean deleteLibrarian(int librarianId) {
        String query = "DELETE FROM librarians WHERE librarian_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, librarianId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting librarian: " + e.getMessage());
            return false;
        }
    }

    // Get all librarians
    public List<Librarian> getAllLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        String query = "SELECT * FROM librarians ORDER BY name";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                librarians.add(extractLibrarianFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting librarians: " + e.getMessage());
        }
        return librarians;
    }

    // Get librarian by ID
    public Librarian getLibrarianById(int librarianId) {
        String query = "SELECT * FROM librarians WHERE librarian_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, librarianId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractLibrarianFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting librarian: " + e.getMessage());
        }
        return null;
    }

    // Search librarians by name or username
    public List<Librarian> searchLibrarians(String searchTerm) {
        List<Librarian> librarians = new ArrayList<>();
        String query = "SELECT * FROM librarians WHERE name LIKE ? OR username LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String pattern = "%" + searchTerm + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                librarians.add(extractLibrarianFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error searching librarians: " + e.getMessage());
        }
        return librarians;
    }

    // Update last login time
    private void updateLastLogin(int librarianId) {
        String query = "UPDATE librarians SET last_login = CURRENT_TIMESTAMP WHERE librarian_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, librarianId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating last login: " + e.getMessage());
        }
    }

    // Helper method to create Librarian object from ResultSet
    private Librarian extractLibrarianFromResultSet(ResultSet rs) throws SQLException {
        Librarian librarian = new Librarian();
        librarian.setLibrarianId(rs.getInt("librarian_id"));
        librarian.setName(rs.getString("name"));
        librarian.setUsername(rs.getString("username"));
        librarian.setPassword(rs.getString("password"));
        librarian.setEmail(rs.getString("email"));
        librarian.setRole(rs.getString("role"));

        Timestamp lastLogin = rs.getTimestamp("last_login");
        if (lastLogin != null) {
            librarian.setLastLogin(lastLogin.toLocalDateTime());
        }
        return librarian;
    }
}