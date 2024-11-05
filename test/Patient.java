import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String patientID;
    private String name;
    private String dateOfBirth;
    private String address;

    // File path for storing patient records
    private static final String PATIENT_FILE = "patients.txt";

    public Patient(String name, String dateOfBirth, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.patientID = generatePatientID();
    }

    // Generates a unique patient ID
    private String generatePatientID() {
        return "P" + (getAllPatients().size() + 1);
    }

    //manage alternate flows
    public String registerPatient() {
        if (name.isEmpty() || dateOfBirth.isEmpty() || address.isEmpty()) {
            return "Registration failed: All fields are required.";
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE, true))) {
            writer.write(patientID + "," + name + "," + dateOfBirth + "," + address);
            writer.newLine();
            return "Registration successful. Patient ID: " + patientID;
        } catch (IOException e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    // Retrieve all patients from the file
    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATIENT_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                patients.add(new Patient(data[1], data[2], data[3])); // Assuming the order is ID, Name, DOB, Address
                patients.get(patients.size() - 1).patientID = data[0]; // Set the ID for the patient
            }
        } catch (IOException e) {
            System.out.println("Error reading patient file: " + e.getMessage());
        }
        return patients;
    }

    // Getters
    public String getPatientID() {
        return patientID;
    }

    // Other getters and setters can be added here
}