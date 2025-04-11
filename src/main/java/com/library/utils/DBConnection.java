package com.library.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://package com.library.utils;\n" +
            "\n" +
            "import java.sql.Connection;\n" +
            "import java.sql.DriverManager;\n" +
            "import java.sql.SQLException;\n" +
            "\n" +
            "public class DBConnection {\n" +
            "\n" +
            "    private static final String DB_URL = \"jdbc:mysql://your_database_host:your_database_port/your_database_name\";  // Replace with your DB URL\n" +
            "    private static final String DB_USER = \"your_database_user\";      // Replace with your DB username\n" +
            "    private static final String DB_PASSWORD = \"your_database_password\";  // Replace with your DB password\n" +
            "    private static Connection connection;\n" +
            "\n" +
            "    public static Connection getConnection() throws SQLException {\n" +
            "        if (connection == null || connection.isClosed()) {\n" +
            "            try {\n" +
            "                Class.forName(\"com.mysql.cj.jdbc.Driver\");  // Use the appropriate driver for your database (e.g., for MySQL)\n" +
            "                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);\n" +
            "                System.out.println(\"Connected to the database!\"); //Good for debugging\n" +
            "            } catch (ClassNotFoundException e) {\n" +
            "                throw new SQLException(\"Failed to load database driver: \" + e.getMessage());\n" +
            "            }\n" +
            "        }\n" +
            "        return connection;\n" +
            "    }\n" +
            "\n" +
            "    //  You might want to add a method to close the connection when the application exits.\n" +
            "    public static void closeConnection() throws SQLException {\n" +
            "        if (connection != null && !connection.isClosed()) {\n" +
            "            connection.close();\n" +
            "            System.out.println(\"Disconnected from the database.\");\n" +
            "        }\n" +
            "    }\n" +
            "}\n";  // Replace with your DB URL
    private static final String DB_USER = "Root";      // Replace with your DB username
    private static final String DB_PASSWORD = "160322057@Busayo";  // Replace with your DB password
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  // Use the appropriate driver for your database (e.g., for MySQL)
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Connected to the database!"); //Good for debugging
            } catch (ClassNotFoundException e) {
                throw new SQLException("Failed to load database driver: " + e.getMessage());
            }
        }
        return connection;
    }

    //  You might want to add a method to close the connection when the application exits.
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Disconnected from the database.");
        }
    }
}
