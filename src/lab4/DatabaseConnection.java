package lab4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/t_db";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Blackops1871!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
