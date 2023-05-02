package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Trip {
    public void addTrip(Scanner scanner) throws SQLException {
        System.out.println("Enter the Trip Number:");
        int tripNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Start Location Name:");
        String startLocationName = scanner.nextLine();

        System.out.println("Enter the Destination Name:");
        String destinationName = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Trip (TripNumber, StartLocationName, DestinationName) VALUES (?, ?, ?)");

        stmt.setInt(1, tripNumber);
        stmt.setString(2, startLocationName);
        stmt.setString(3, destinationName);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Trip added successfully.");
        } else {
            System.out.println("Error adding trip.");
        }

        stmt.close();
        conn.close();
    }
    public void displaySchedule(Scanner scanner) throws SQLException {
        System.out.print("Enter the StartLocationName: ");
        String startLocationName = scanner.nextLine();
        System.out.print("Enter the DestinationName: "); 
        String destinationName = scanner.nextLine();
        System.out.print("Enter the Date (yyyy-MM-dd): ");
        String dateStr = scanner.next();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TripOffering JOIN Trip ON Trip.TripNumber = TripOffering.TripNumber WHERE StartLocationName = ? AND DestinationName = ? AND Date = ? ORDER BY ScheduledStartTime");
        stmt.setString(1, startLocationName);
        stmt.setString(2, destinationName);
        stmt.setDate(3, java.sql.Date.valueOf(dateStr));

        ResultSet rs = stmt.executeQuery();
        System.out.println("\nSchedule for StartLocationName: " + startLocationName + ", DestinationName: " + destinationName + ", Date: " + dateStr);
        while (rs.next()) {
            System.out.println("TripNumber: " + rs.getInt("TripNumber")
                    + ", ScheduledStartTime: " + rs.getTime("ScheduledStartTime")
                    + ", ScheduledArrivalTime: " + rs.getTime("ScheduledArrivalTime")
                    + ", DriverName: " + rs.getString("DriverName")
                    + ", BusID: " + rs.getInt("BusID"));
        }

        rs.close();
        stmt.close();
        conn.close();
    }

}
