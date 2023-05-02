package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Driver {
    public void addDriver(Scanner scanner) throws SQLException {
        System.out.println("Enter the Driver Name:");
        String driverName = scanner.nextLine();

        System.out.println("Enter the Driver Telephone Number:");
        String driverTelephoneNumber = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Driver (DriverName, DriverTelephoneNumber) VALUES (?, ?)");

        stmt.setString(1, driverName);
        stmt.setString(2, driverTelephoneNumber);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Driver added successfully.");
        } else {
            System.out.println("Error adding driver.");
        }

        stmt.close();
        conn.close();
    }
    public void displayWeeklySchedule(Scanner scanner) throws SQLException, ParseException {
        System.out.print("Enter the DriverName: ");
        String driverName = scanner.nextLine();
        System.out.print("Enter the start date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter the end date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date startDate = sdf.parse(startDateStr);
        java.util.Date endDate = sdf.parse(endDateStr);

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TripOffering WHERE DriverName = ? AND Date BETWEEN ? AND ? ORDER BY Date, ScheduledStartTime");
        stmt.setString(1, driverName);
        stmt.setDate(2, new java.sql.Date(startDate.getTime()));
        stmt.setDate(3, new java.sql.Date(endDate.getTime()));

        ResultSet rs = stmt.executeQuery();
        System.out.println("\nWeekly schedule for Driver: " + driverName);
        while (rs.next()) {
            System.out.println("TripNumber: " + rs.getInt("TripNumber")
                    + ", Date: " + rs.getDate("Date")
                    + ", ScheduledStartTime: " + rs.getTime("ScheduledStartTime")
                    + ", ScheduledArrivalTime: " + rs.getTime("ScheduledArrivalTime")
                    + ", BusID: " + rs.getInt("BusID"));
        }

        rs.close();
        stmt.close();
        conn.close();
    }

}

