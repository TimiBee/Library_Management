package com.library.dao;

import com.library.models.Book;
import com.library.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Add a new book
    public boolean addBook(Book book) {
        String query = "INSERT INTO books (title, author, publisher, isbn, category, quantity, available) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setString(4, book.getIsbn());
            stmt.setString(5, book.getCategory());
            stmt.setInt(6, book.getQuantity());
            stmt.setInt(7, book.getQuantity()); // Initially, available = quantity

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
            return false;
        }
    }

    // Update an existing book
    public boolean updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, publisher = ?, " +
                "isbn = ?, category = ?, quantity = ?, available = ? " +
                "WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setString(4, book.getIsbn());
            stmt.setString(5, book.getCategory());
            stmt.setInt(6, book.getQuantity());
            stmt.setInt(7, book.getAvailable());
            stmt.setInt(8, book.getBookId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
            return false;
        }
    }

    // Delete a book
    public boolean deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
            return false;
        }
    }

    // Get a book by ID
    public Book getBookById(int bookId) {
        String query = "SELECT * FROM books WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractBookFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error getting book by ID: " + e.getMessage());
        }

        return null;
    }

    // Get all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books ORDER BY title";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(extractBookFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error getting all books: " + e.getMessage());
        }

        return books;
    }

    // Search books by title, author, or ISBN
    public List<Book> searchBooks(String searchTerm) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ? ORDER BY title";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String searchPattern = "%" + searchTerm + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(extractBookFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error searching books: " + e.getMessage());
        }

        return books;
    }

    // Update book availability after issue or return
    public boolean updateBookAvailability(int bookId, boolean isIssued) {
        String query = "UPDATE books SET available = available " + (isIssued ? "- 1" : "+ 1") +
                " WHERE book_id = ? AND " + (isIssued ? "available > 0" : "available < quantity");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating book availability: " + e.getMessage());
            return false;
        }
    }

    // Check if a book is available for issuing
    public boolean isBookAvailable(int bookId) {
        String query = "SELECT available FROM books WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("available") > 0;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error checking book availability: " + e.getMessage());
        }

        return false;
    }

    // Helper method to extract book from ResultSet
    private Book extractBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPublisher(rs.getString("publisher"));
        book.setIsbn(rs.getString("isbn"));
        book.setCategory(rs.getString("category"));
        book.setQuantity(rs.getInt("quantity"));
        book.setAvailable(rs.getInt("available"));

        Timestamp timestamp = rs.getTimestamp("added_date");
        if (timestamp != null) {
            book.setAddedDate(timestamp.toLocalDateTime());
        }

        return book;
    }
}