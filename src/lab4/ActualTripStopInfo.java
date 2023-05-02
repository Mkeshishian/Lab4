package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ActualTripStopInfo {
    public void addActualTripStopInfo(Scanner scanner) throws SQLException {
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

        System.out.println("Enter the Stop Number:");
        int stopNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Scheduled Arrival Time (HH:mm):");
        String scheduledArrivalTimeStr = scanner.nextLine();
        Date scheduledArrivalTime;
        try {
            scheduledArrivalTime = new SimpleDateFormat("HH:mm").parse(scheduledArrivalTimeStr);
        } catch (ParseException e) {
            System.out.println("Invalid time format. Please try again.");
            return;
        }

        System.out.println("Enter the Actual Start Time (HH:mm):");
        String actualStartTimeStr = scanner.nextLine();
        Date actualStartTime;
        try {
            actualStartTime = new SimpleDateFormat("HH:mm").parse(actualStartTimeStr);
        } catch (ParseException e) {
            System.out.println("Invalid time format. Please try again.");
            return;
        }

        System.out.println("Enter the Actual Arrival Time (HH:mm):");
        String actualArrivalTimeStr = scanner.nextLine();
        Date actualArrivalTime;
        try {
            actualArrivalTime = new SimpleDateFormat("HH:mm").parse(actualArrivalTimeStr);
        } catch (ParseException e) {
            System.out.println("Invalid time format. Please try again.");
            return;
        }

        System.out.println("Enter the Number of Passengers In:");
        int numberOfPassengerIn = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Number of Passengers Out:");
        int numberOfPassengerOut = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Actual trip stop info added successfully.");
        
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO ActualTripStopInfo (TripNumber, Date, ScheduledStartTime, StopNumber, ScheduledArrivalTime, ActualStartTime, ActualArrivalTime, NumberOfPassengerIn, NumberOfPassengerOut) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        stmt.setInt(1, tripNumber);
        stmt.setDate(2, new java.sql.Date(date.getTime()));
        stmt.setTime(3, new java.sql.Time(scheduledStartTime.getTime()));
        stmt.setInt(4, stopNumber);
        stmt.setTime(5, new java.sql.Time(scheduledArrivalTime.getTime()));
        stmt.setTime(6, new java.sql.Time(actualStartTime.getTime()));
        stmt.setTime(7, new java.sql.Time(actualArrivalTime.getTime()));
        stmt.setInt(8, numberOfPassengerIn);
        stmt.setInt(9, numberOfPassengerOut);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Actual trip stop info added successfully.");
            
            // update the actual start time and actual arrival time in TripOffering table
            Connection conn2 = DatabaseConnection.getConnection();
            PreparedStatement stmt2 = conn2.prepareStatement("UPDATE TripOffering SET ActualStartTime = ?, ActualArrivalTime = ? WHERE TripNumber = ? AND Date = ? AND ScheduledStartTime = ?");
            stmt2.setTime(1, new java.sql.Time(actualStartTime.getTime()));
            stmt2.setTime(2, new java.sql.Time(actualArrivalTime.getTime()));
            stmt2.setInt(3, tripNumber);
            stmt2.setDate(4, new java.sql.Date(date.getTime()));
            stmt2.setTime(5, new java.sql.Time(scheduledStartTime.getTime()));
            
            int rowsAffected2 = stmt2.executeUpdate();
            if (rowsAffected2 > 0) {
                System.out.println("Actual start time and actual arrival time updated successfully in TripOffering table.");
            } else {
                System.out.println("Error updating actual start time and actual arrival time in TripOffering table.");
            }
            
            stmt2.close();
            conn2.close();
        } else {
            System.out.println("Error adding actual trip stop info.");
        }

        stmt.close();
        conn.close();
    }
}

