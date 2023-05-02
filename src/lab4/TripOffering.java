package lab4;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TripOffering {
	public void addTripOffering(Scanner scanner) throws SQLException {
        System.out.println("Enter the Trip Number:");
        int tripNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Date (yyyy-MM-dd):");
        String dateStr = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        System.out.println("Enter the Scheduled Start Time (HH:mm):");
        String scheduledStartTimeStr = scanner.nextLine();
        Date scheduledStartTime;
        try {
            scheduledStartTime = new SimpleDateFormat("HH:mm").parse(scheduledStartTimeStr);
        } catch (ParseException e) {
            System.out.println("Invalid time format. Please try again.");
            return;
        }

        System.out.println("Enter the Scheduled Arrival Time (HH:mm):");
        String scheduledArrivalTimeStr = scanner.nextLine();
        Date scheduledArrivalTime;
        try {
            scheduledArrivalTime = new SimpleDateFormat("HH:mm").parse(scheduledArrivalTimeStr);
        } catch (ParseException e) {
            System.out.println("Invalid time format. Please try again.");
            return;
        }

        System.out.println("Enter the Driver Name:");
        String driverName = scanner.nextLine();

        System.out.println("Enter the Bus ID:");
        int busID = scanner.nextInt();
        scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO TripOffering (TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, DriverName, BusID) VALUES (?, ?, ?, ?, ?, ?)");

        stmt.setInt(1, tripNumber);
        stmt.setDate(2, new java.sql.Date(date.getTime()));
        stmt.setTime(3, new java.sql.Time(scheduledStartTime.getTime()));
        stmt.setTime(4, new java.sql.Time(scheduledArrivalTime.getTime()));
        stmt.setString(5, driverName);
        stmt.setInt(6, busID);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Trip offering added successfully.");
        } else {
            System.out.println("Error adding trip offering.");
        }

        stmt.close();
        conn.close();
    }


    public void editSchedule(Scanner scanner) throws SQLException {
        System.out.println("\nEdit the schedule:");
        System.out.println("1. Delete a trip offering");
        System.out.println("2. Add a trip offering");
        System.out.println("3. Change the driver for a trip offering");
        System.out.println("4. Change the bus for a trip offering");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                deleteTripOffering(scanner);
                break;
            case 2:
                addTripOffering(scanner);
                break;
            case 3:
                changeDriverForTripOffering(scanner);
                break;
            case 4:
                changeBusForTripOffering(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public void deleteTripOffering(Scanner scanner) throws SQLException {
        System.out.print("Enter the TripNumber: ");
        int tripNumber = scanner.nextInt();
        System.out.print("Enter the Date (yyyy-MM-dd): ");
        String dateStr = scanner.next();
        System.out.print("Enter the ScheduledStartTime (HH:mm:ss): ");
        String startTimeStr = scanner.next();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM TripOffering WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?");

        stmt.setInt(1, tripNumber);
        stmt.setDate(2, java.sql.Date.valueOf(dateStr));
        stmt.setTime(3, java.sql.Time.valueOf(startTimeStr));

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Trip offering deleted successfully.");
        } else {
            System.out.println("Error deleting trip offering.");
        }

        stmt.close();
        conn.close();
    }

    public void changeDriverForTripOffering(Scanner scanner) throws SQLException {
        System.out.print("Enter the TripNumber: ");
        int tripNumber = scanner.nextInt();
        System.out.print("Enter the Date (yyyy-MM-dd): ");
        String dateStr = scanner.next();
        System.out.print("Enter the ScheduledStartTime (HH:mm:ss): ");
        String startTimeStr = scanner.next();
        System.out.print("Enter the new DriverName: ");
        String newDriverName = scanner.next();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE TripOffering SET DriverName = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?");

        stmt.setString(1, newDriverName);
        stmt.setInt(2, tripNumber);
        stmt.setDate(3, java.sql.Date.valueOf(dateStr));
        stmt.setTime(4, java.sql.Time.valueOf(startTimeStr));

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Driver for trip offering updated successfully.");
        } else {
            System.out.println("Error updating driver for trip offering.");
        }

        stmt.close();
        conn.close();
    }

    public void changeBusForTripOffering(Scanner scanner) throws SQLException {
        System.out.print("Enter the TripNumber: ");
        int tripNumber = scanner.nextInt();
        System.out.print("Enter the Date (yyyy-MM-dd): ");
        String dateStr = scanner.next();
        System.out.print("Enter the ScheduledStartTime (HH:mm:ss): ");
        String startTimeStr = scanner.next();
        System.out.print("Enter the new BusID: ");
        int newBusID = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE TripOffering SET BusID = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?");

        stmt.setInt(1, newBusID);
        stmt.setInt(2, tripNumber);
        stmt.setDate(3, java.sql.Date.valueOf(dateStr));
        stmt.setTime(4, java.sql.Time.valueOf(startTimeStr));

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Bus for trip offering updated successfully.");
        } else {
            System.out.println("Error updating bus for trip offering.");
        }

        stmt.close();
        conn.close();
    }
}
