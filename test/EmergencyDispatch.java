import java.io.*;
import java.util.*;

public class EmergencyDispatch {

    private List<Ambulance> ambulances = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();

    public static void main(String[] args) {
        EmergencyDispatch dispatchSystem = new EmergencyDispatch();
        dispatchSystem.loadAmbulances(".\\test\\files\\ambulances.txt");
        dispatchSystem.loadDrivers(".\\test\\files\\drivers.txt");
        
        

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("---- Emergency Dispatch System ----");

        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Enter a new patient");
            System.out.println("2. Free a driver and ambulance");
            System.out.println("3. Display ambulance and driver status");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> dispatchSystem.handleNewPatient(scanner);
                case "2" -> dispatchSystem.freeResources(scanner);
                case "3" -> dispatchSystem.displayStatus();
                case "4" -> {
                    running = false;
                    System.out.println("Exiting the system.");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void handleNewPatient(Scanner scanner) {
        System.out.print("\nEnter patient name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter patient condition: ");
        String patientCondition = scanner.nextLine();

        System.out.print("Enter Estimated Time of Arrival (ETA): ");
        String eta = scanner.nextLine();  // Get ETA

        System.out.print("Enter room number: ");
        String room = scanner.nextLine();  // Get room number

        EmergencyPatient patient = new EmergencyPatient(patientName, patientCondition);

        System.out.println("\nDispatching emergency resources...");
        if (dispatchEmergency(patient, eta, room)) {
            System.out.println("Emergency resources successfully dispatched!");
            // Save room data to the correct file based on room type
            saveEmergencyRoom(room, patientName, eta);  // Save to emergencyroom.txt if it's an ER
        } else {
            System.out.println("No available ambulances or drivers at this time.");
        }

        System.out.println("\nCurrent Ambulance and Driver Status:");
        displayStatus();
    }

    private void saveEmergencyRoom(String room, String patient, String eta) {
        // Check if the room is an emergency room based on ERid or Rid
        if (room.startsWith("ER")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\test\\files\\emergencyroom.txt", true))) {
                writer.write(room + ", " + patient + ", " + eta);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Error writing to emergencyroom.txt: " + e.getMessage());
            }
        } else {
            System.out.println("This room is not an emergency room and will not be saved to emergencyroom.txt.");
        }
    }

    private void freeResources(Scanner scanner) {
        System.out.println("\nFreeing resources:");
        System.out.print("Enter ambulance ID to free: ");
        String ambulanceId = scanner.nextLine();

        Ambulance ambulance = findAmbulanceById(ambulanceId);
        if (ambulance == null || ambulance.isAvailable()) {
            System.out.println("Invalid ambulance ID or ambulance is already available.");
            return;
        }

        System.out.print("Enter driver ID to free: ");
        int driverId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Driver driver = findDriverById(driverId);
        if (driver == null || driver.isAvailable()) {
            System.out.println("Invalid driver ID or driver is already available.");
            return;
        }

        // Free resources
        ambulance.setAvailable(true);
        ambulance.setAssignedPatient("None");
        ambulance.setAssignedCondition("None");
        driver.setAvailable(true);
        driver.setAssignedAmbulance(null); // Clear the assigned ambulance for the driver

        System.out.println("Resources freed: Ambulance " + ambulanceId + " and Driver " + driverId);
        saveAmbulances(".\\test\\files\\ambulances.txt"); // Update ambulances file
    }

    public void loadAmbulances(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",\\s*");
                String id = data[0];
                boolean available = data[1].equalsIgnoreCase("Available");
                String assignedPatient = data.length > 2 ? data[2] : "None";
                String assignedCondition = data.length > 3 ? data[3] : "None";
                String eta = data.length > 4 ? data[4] : "N/A";  // Default "N/A" for ETA
                String room = data.length > 5 ? data[5] : "Unknown";  // Default "Unknown" for room
                ambulances.add(new Ambulance(id, available, assignedPatient, assignedCondition, eta, room));
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
    }

    public void loadDrivers(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",\\s*");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                boolean available = data[2].equalsIgnoreCase("Available");
                String assignedAmbulance = data.length > 3 ? data[3] : null;
                drivers.add(new Driver(id, name, available, assignedAmbulance));
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
    }

    public boolean dispatchEmergency(EmergencyPatient patient, String eta, String room) {
        Ambulance availableAmbulance = null;
        Driver availableDriver = null;

        // Find an available ambulance
        for (Ambulance ambulance : ambulances) {
            if (ambulance.isAvailable()) {
                availableAmbulance = ambulance;
                break;
            }
        }

        // Find an available driver
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                availableDriver = driver;
                break;
            }
        }

        if (availableAmbulance != null && availableDriver != null) {
            // Assign the ambulance and driver
            availableAmbulance.setAvailable(false);
            availableAmbulance.setAssignedPatient(patient.getName());
            availableAmbulance.setAssignedCondition(patient.getCondition());
            availableAmbulance.setEta(eta);  // Set ETA
            availableAmbulance.setRoom(room);  // Set room
            availableDriver.setAvailable(false);
            availableDriver.setAssignedAmbulance(availableAmbulance.getId()); // Assign ambulance to driver

            System.out.println("Dispatch Details:");
            System.out.println("Patient Name: " + patient.getName());
            System.out.println("Condition: " + patient.getCondition());
            System.out.println("Ambulance Assigned: " + availableAmbulance.getId());
            System.out.println("Driver Assigned: " + availableDriver.getName());
            System.out.println("ETA: " + availableAmbulance.getEta());
            System.out.println("Room: " + availableAmbulance.getRoom());

            return true;
        }

        return false; // No resources available
    }

    public void saveAmbulances(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Ambulance ambulance : ambulances) {
                writer.write(ambulance.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to " + filename + ": " + e.getMessage());
        }
    }

    private Ambulance findAmbulanceById(String id) {
        for (Ambulance ambulance : ambulances) {
            if (ambulance.getId().equalsIgnoreCase(id)) {
                return ambulance;
            }
        }
        return null;
    }

    private Driver findDriverById(int id) {
        for (Driver driver : drivers) {
            if (driver.getId() == id) {
                return driver;
            }
        }
        return null;
    }

    public void displayStatus() {
        System.out.println("\n--- Ambulance Status ---");
        if (ambulances.isEmpty()) {
            System.out.println("No ambulance data available.");
        } else {
            for (Ambulance ambulance : ambulances) {
                System.out.println(ambulance);
            }
        }
    
        System.out.println("\n--- Driver Status ---");
        if (drivers.isEmpty()) {
            System.out.println("No driver data available.");
        } else {
            for (Driver driver : drivers) {
                System.out.println(driver);
            }
        }
    }
}
