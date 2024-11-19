import java.util.List;
import java.util.Scanner;

public class uiTests {
    private static List<Patient> patients = Patient.getAllPatients();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            
            System.out.println("\n=== Patient Management System ===");
            System.out.println("1. Register Patient");
            System.out.println("2. Access Patient Record");
            System.out.println("3. Update Patient Medical Record");
            System.out.println("4. Retrieve All Patients");
            System.out.println("5. Log Record Access Attempt");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> accessPatientRecord();
                case 3 -> updateMedicalRecord();
                case 4 -> retrieveAllPatients();
                case 5 -> logRecordAccessAttempt();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient birthdate (YYYY-MM-DD): ");
        String birthdate = scanner.nextLine();
        System.out.print("Enter patient address: ");
        String address = scanner.nextLine();

        Patient patient = new Patient(name, birthdate, address);
        String result = patient.registerPatient();
        patients.add(patient);
        System.out.println(result + " - Patient Name: " + patient.getPatientName());
    }

    private static void accessPatientRecord() {
        System.out.print("Enter Patient ID to access: ");
        String patientID = scanner.nextLine();
        
        // Load all patients from the file
        
        
        // Check if patient with given ID exists
        for (Patient p : patients) {
            if (p.getPatientID().equals(patientID)) {
                boolean accessResult = p.accessPatientRecord(patientID);
                System.out.println("Access patient " + patientID + ": " 
                    + (accessResult ? "Success - " + p.getPatientName() : "Failure"));
                return;
            }
        }
        
        // If no patient was found with the specified ID
        System.out.println("Patient not found.");
    }

    private static void updateMedicalRecord() {
        System.out.print("Enter Patient ID to update: ");
        String patientID = scanner.nextLine();
        for (Patient p : patients) {
            if (p.getPatientID().equals(patientID)) {
                System.out.print("Enter update for medical record: ");
                String update = scanner.nextLine();
                String result = p.updateMedicalRecord(update);
                System.out.println(result);
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    private static void retrieveAllPatients() {
        System.out.println("=== All Registered Patients ===");
        for (Patient p : patients) {
            System.out.println("Patient ID: " + p.getPatientID() + ", Name: " + p.getPatientName() + " DOB: " + p.dateOfBirth +" Medication: " + p.medicalRecord);
        }
    }

    private static void logRecordAccessAttempt() {
        System.out.print("Enter Patient ID for access logging: ");
        String patientID = scanner.nextLine();
        System.out.print("Enter the name of the person accessing the record: ");
        String accessorName = scanner.nextLine();

        for (Patient p : patients) {
            if (p.getPatientID().equals(patientID)) {
                boolean accessResult = p.accessPatientRecord(patientID);
                boolean logResult = p.logRecordAccess(accessorName, patientID, accessResult);
                System.out.println("Log record access: " + (logResult ? "Logged" : "Failed to log"));
                return;
            }
        }
        System.out.println("Patient not found.");
    }
}