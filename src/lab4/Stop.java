package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Stop {
    public void addStop(Scanner scanner) throws SQLException {
        System.out.println("Enter the Stop Number:");
        int stopNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Stop Address:");
        String stopAddress = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Stop (StopNumber, StopAddress) VALUES (?, ?)");

        stmt.setInt(1, stopNumber);
        stmt.setString(2, stopAddress);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Stop added successfully.");
        } else {
            System.out.println("Error adding stop.");
        }

        stmt.close();
        conn.close();
    }
}

