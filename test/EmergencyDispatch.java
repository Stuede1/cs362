import java.io.*;
import java.util.*;

public class EmergencyDispatch {

    private List<Ambulance> ambulances = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();

    public static void main(String[] args) {
        EmergencyDispatch dispatchSystem = new EmergencyDispatch();
        dispatchSystem.loadAmbulances(".\\files\\ambulances.txt");
        dispatchSystem.loadDrivers(".\\files\\drivers.txt");

        Scanner scanner = new Scanner(System.in);

        System.out.println("---- Emergency Dispatch System ----");
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter patient condition: ");
        String patientCondition = scanner.nextLine();

        EmergencyPatient patient = new EmergencyPatient(patientName, patientCondition);

        System.out.println("\nDispatching emergency resources...");
        if (dispatchSystem.dispatchEmergency(patient)) {
            System.out.println("Emergency resources successfully dispatched!");
            dispatchSystem.saveAmbulances(".\\files\\ambulances.txt"); // Update ambulances file
        } else {
            System.out.println("No available ambulances or drivers at this time.");
        }

        System.out.println("\nCurrent Ambulance and Driver Status:");
        dispatchSystem.displayStatus();

        scanner.close();
    }

    public void loadAmbulances(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",\\s*");
                String id = data[0];
                boolean available = data[1].equalsIgnoreCase("Available");
                String assignedPatient = data.length > 2 ? data[2] : "None";
                ambulances.add(new Ambulance(id, available, assignedPatient));
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
                drivers.add(new Driver(id, name, available));
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
    }

    public boolean dispatchEmergency(EmergencyPatient patient) {
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
                availableDriver.setAvailable(false);
    
                System.out.println("Dispatch Details:");
                System.out.println("Patient Name: " + patient.getName());
                System.out.println("Condition: " + patient.getCondition());
            System.out.println("Ambulance Assigned: " + availableAmbulance.getId());
            System.out.println("Driver Assigned: " + availableDriver.getName());

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

    public void displayStatus() {
        System.out.println("\nAmbulance Status:");
        for (Ambulance ambulance : ambulances) {
            System.out.println(ambulance);
        }

        System.out.println("\nDriver Status:");
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }
}