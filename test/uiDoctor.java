
import java.util.List;
import java.util.Scanner;

public class uiDoctor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=== Welcome to the Doctors Management System ===");

        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. View all doctors");
            System.out.println("2. Save a new doctor");
            System.out.println("3. Find a doctor by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> viewAllDoctors();
                case 2 -> saveNewDoctor(scanner);
                case 3 -> findDoctorByID(scanner);
                case 4 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // Method to view all doctors
    private static void viewAllDoctors() {
        System.out.println("\n=== Viewing All Doctors ===");
        List<Doctor> allDoctors = Doctor.getAllDoctors();
        if (allDoctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            for (Doctor doc : allDoctors) {
                System.out.println("Doctor ID: " + doc.getDoctorID() + ", Name: " + doc.getDoctorName());
            }
        }
    }

    // Method to save a new doctor
    private static void saveNewDoctor(Scanner scanner) {
        System.out.println("\n=== Saving New Doctor ===");
        System.out.print("Enter Doctor ID: ");
        String doctorID = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctorName = scanner.nextLine();

        Doctor newDoctor = new Doctor(doctorID, doctorName);
        System.out.println(newDoctor.saveDoctor());
    }

    // Method to find a doctor by ID
    private static void findDoctorByID(Scanner scanner) {
        System.out.println("\n=== Finding Doctor by ID ===");
        System.out.print("Enter Doctor ID to search: ");
        String searchID = scanner.nextLine();

        Doctor foundDoctor = Doctor.findDoctorByID(searchID);
        if (foundDoctor != null) {
            System.out.println("Found Doctor - ID: " + foundDoctor.getDoctorID() + ", Name: " + foundDoctor.getDoctorName());
        } else {
            System.out.println("Doctor with ID " + searchID + " not found.");
        }
    }
}
