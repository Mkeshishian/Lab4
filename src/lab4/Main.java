package lab4;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Trip trip = new Trip();
        TripOffering tripOffering = new TripOffering();
        Bus bus = new Bus();
        Driver driver = new Driver();
        Stop stop = new Stop();
        ActualTripStopInfo actualTripStopInfo = new ActualTripStopInfo();
        TripStopInfo tripStopInfo = new TripStopInfo();

        while (true) {
            System.out.println("\n\nPomona Transit System");
            System.out.println("1. Display the schedule of all trips");
            System.out.println("2. Edit the schedule");
            System.out.println("3. Display the stops of a given trip");
            System.out.println("4. Display the weekly schedule of a given driver");
            System.out.println("5. Add a driver");
            System.out.println("6. Add a bus");
            System.out.println("7. Delete a bus");
            System.out.println("8. Record actual data of a trip offering");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    trip.displaySchedule(scanner);
                    break;
                case 2:
                    tripOffering.editSchedule(scanner);
                    break;
                case 3:
                    tripStopInfo.displayStops(scanner);
                    break;
                case 4:
                    driver.displayWeeklySchedule(scanner);
                    break;
                case 5:
                    driver.addDriver(scanner);
                    break;
                case 6:
                    bus.addBus(scanner);
                    break;
                case 7:
                    bus.deleteBus(scanner);
                    break;
                case 8:
                    actualTripStopInfo.addActualTripStopInfo(scanner);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

