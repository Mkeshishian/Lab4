package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Bus {
    public void addBus(Scanner scanner) throws SQLException {
        System.out.println("Enter the Bus ID:");
        int busID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Model:");
        String model = scanner.nextLine();

        System.out.println("Enter the Year:");
        int year = scanner.nextInt();
        scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Bus (BusID, Model, Year) VALUES (?, ?, ?)");

        stmt.setInt(1, busID);
        stmt.setString(2, model);
        stmt.setInt(3, year);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Bus added successfully.");
        } else {
            System.out.println("Error adding bus.");
        }

        stmt.close();
        conn.close();
    }
    public void deleteBus(Scanner scanner) throws SQLException {
        System.out.print("Enter the BusID: ");
        int busID = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Bus WHERE BusID = ?");
        stmt.setInt(1, busID);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Bus deleted successfully.");
        } else {
            System.out.println("Error deleting bus.");
        }

        stmt.close();
        conn.close();
    }
}

