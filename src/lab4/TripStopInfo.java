package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TripStopInfo {
    public void addTripStopInfo(Scanner scanner) throws SQLException {
        System.out.println("Enter the Trip Number:");
        int tripNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Stop Number:");
        int stopNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Sequence Number:");
        int sequenceNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Driving Time (in minutes):");
        int drivingTime = scanner.nextInt();
        scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO TripStopInfo (TripNumber, StopNumber, SequenceNumber, DrivingTime) VALUES (?, ?, ?, ?)");

        stmt.setInt(1, tripNumber);
        stmt.setInt(2, stopNumber);
        stmt.setInt(3, sequenceNumber);
        stmt.setInt(4, drivingTime);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Trip stop info added successfully.");
        } else {
            System.out.println("Error adding trip stop info.");
        }

        stmt.close();
        conn.close();
    }
    public void displayStops(Scanner scanner) throws SQLException {
        System.out.print("Enter the TripNumber: ");
        int tripNumber = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TripStopInfo WHERE TripNumber = ? ORDER BY SequenceNumber");
        stmt.setInt(1, tripNumber);

        ResultSet rs = stmt.executeQuery();
        System.out.println("\nStops for TripNumber: " + tripNumber);
        while (rs.next()) {
            System.out.println("StopNumber: " + rs.getInt("StopNumber")
                    + ", SequenceNumber: " + rs.getInt("SequenceNumber")
                    + ", DrivingTime: " + rs.getTime("DrivingTime"));
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}
