package com.library.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/library_management";
    private static final String USER = "root";
    private static final String PASSWORD = "160322057@Busayo"; // Replace with your MySQL password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish Connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error while connecting to the database: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection(); // Test connection
    }
}