package com.library.dao;

import com.library.models.Transaction;
import com.library.utils.DBConnection;
import com.library.utils.DateUtils;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Issue a new book
    public boolean issueBook(Transaction transaction) {
        String query = "INSERT INTO transactions (book_id, user_id, issue_date, due_date) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transaction.getBookId());
            stmt.setInt(2, transaction.getUserId());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.getIssueDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(transaction.getDueDate()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error issuing book: " + e.getMessage());
            return false;
        }
    }

    // Return a book
    public boolean returnBook(int transactionId) {
        String query = "UPDATE transactions SET return_date = CURRENT_TIMESTAMP, status = 'returned', "
                + "fine = ? WHERE transaction_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            Transaction transaction = getTransactionById(transactionId);
            double fine = calculateFine(transaction.getDueDate(), LocalDateTime.now());

            stmt.setDouble(1, fine);
            stmt.setInt(2, transactionId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error returning book: " + e.getMessage());
            return false;
        }
    }

    // Calculate overdue fine
    private double calculateFine(LocalDateTime dueDate, LocalDateTime returnDate) {
        if (returnDate.isBefore(dueDate)) return 0.0;

        long daysOverdue = Duration.between(dueDate, returnDate).toDays();
        return daysOverdue * 2.0; // $2 per day fine
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT t.*, b.title, u.name FROM transactions t "
                + "JOIN books b ON t.book_id = b.book_id "
                + "JOIN users u ON t.user_id = u.user_id "
                + "ORDER BY t.issue_date DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Transaction transaction = extractTransactionFromResultSet(rs);
                transaction.setBookTitle(rs.getString("title"));
                transaction.setUserName(rs.getString("name"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.err.println("Error getting transactions: " + e.getMessage());
        }
        return transactions;
    }

    // Get transactions by user
    public List<Transaction> getTransactionsByUser(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT t.*, b.title, u.name FROM transactions t "
                + "JOIN books b ON t.book_id = b.book_id "
                + "JOIN users u ON t.user_id = u.user_id "
                + "WHERE t.user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = extractTransactionFromResultSet(rs);
                transaction.setBookTitle(rs.getString("title"));
                transaction.setUserName(rs.getString("name"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.err.println("Error getting user transactions: " + e.getMessage());
        }
        return transactions;
    }

    // Get transaction by ID
    public Transaction getTransactionById(int transactionId) {
        String query = "SELECT * FROM transactions WHERE transaction_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractTransactionFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting transaction: " + e.getMessage());
        }
        return null;
    }

    // Update overdue statuses (to be called periodically)
    public void updateOverdueStatuses() {
        String query = "UPDATE transactions SET status = 'overdue' "
                + "WHERE due_date < CURRENT_TIMESTAMP AND return_date IS NULL";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Error updating overdue statuses: " + e.getMessage());
        }
    }

    // Helper method to create Transaction object from ResultSet
    private Transaction extractTransactionFromResultSet(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rs.getInt("transaction_id"));
        transaction.setBookId(rs.getInt("book_id"));
        transaction.setUserId(rs.getInt("user_id"));
        transaction.setIssueDate(rs.getTimestamp("issue_date").toLocalDateTime());
        transaction.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());

        Timestamp returnDate = rs.getTimestamp("return_date");
        if (returnDate != null) {
            transaction.setReturnDate(returnDate.toLocalDateTime());
        }

        transaction.setFine(rs.getDouble("fine"));
        transaction.setStatus(rs.getString("status"));
        return transaction;
    }
}