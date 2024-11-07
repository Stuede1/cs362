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
    private String medicalRecord;

    // File path for storing patient records
    private static final String PATIENT_FILE = "patients.txt";

    public Patient(String name, String dateOfBirth, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.medicalRecord = "";
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
            writer.write(patientID + "," + name + "," + dateOfBirth + "," + address + "," + medicalRecord);
            writer.newLine();
            return "Registration successful. Patient ID: " + patientID;
        } catch (IOException e) {
            return "Registration failed: " + e.getMessage();
        }
    }
    // Update patient medical record
    public String updateMedicalRecord(String newRecord) {
        this.medicalRecord += newRecord + "\n";
        return saveUpdates();
    }
    // Save updates to the patient file
    private String saveUpdates() {
        List<Patient> patients = getAllPatients();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE))) {
            for (Patient patient : patients) {
                if (patient.getPatientID().equals(this.patientID)) {
                    writer.write(patientID + "," + name + "," + dateOfBirth + "," + address + "," + medicalRecord);
                } else {
                    writer.write(patient.patientID + "," + patient.name + "," + patient.dateOfBirth + "," + patient.address + "," + patient.medicalRecord);
                }
                writer.newLine();
            }
            return "Update successful.";
        } catch (IOException e) {
            return "Update failed: " + e.getMessage();
        }
    }

    // Retrieve all patients from the file
    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATIENT_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                // patients.add(new Patient(data[1], data[2], data[3])); // Assuming the order is ID, Name, DOB, Address
                // patients.get(patients.size() - 1).patientID = data[0]; // Set the ID for the patient

                //updated code that will handle medicalRecord
                Patient patient = new Patient(data[1], data[2], data[3]);
                patient.medicalRecord = data[4];
                patients.get(patients.size() - 1).patientID = data[0]; 
                patient.patientID = data[0]; 
                patients.add(patient);
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