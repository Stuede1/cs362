import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
* @author Yunpeng Lyu, Cole Stuedeman
*
* Manages patient records
*
*/

public class Patient implements PatientRecordInterface {
    private static int patientCounter = 0;
     String patientID;
     String patientName;
     String dateOfBirth;
    String address;
     String medicalRecord;

    // File path for storing patient records
    private static final String PATIENT_FILE = "files\\patients.txt";

    public Patient(String patientName, String dateOfBirth, String address) {
        this.patientName = patientName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.medicalRecord = "";
        this.patientID = generatePatientID();
    }

    // Generates a unique patient ID
    private String generatePatientID() {
        return "P" + (++patientCounter); // Increment and return new ID
    }

    // manage alternate flows
    public String registerPatient() {
        if (patientName.isEmpty() || dateOfBirth.isEmpty() || address.isEmpty()) {
            return "Registration failed: All fields are required.";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE, true))) {
            writer.write(patientID + "," + patientName + "," + dateOfBirth + "," + address + "," + medicalRecord);
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
                    writer.write(patientID + "," + patientName + "," + dateOfBirth + "," + address + "," + medicalRecord);
                } else {
                    writer.write(patient.patientID + "," + patient.patientName + "," + patient.dateOfBirth + ","
                            + patient.address + "," + patient.medicalRecord);
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
                if (data.length < 5) {
                    System.out.println("Skipping incomplete patient record: " + line);
                    continue;
                }
                Patient patient = new Patient(data[1], data[2], data[3]);
                patient.patientID = data[0];
                patient.medicalRecord = data[4];
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

    @Override
    public boolean accessPatientRecord(String patientId) {
        for (Patient patient : getAllPatients()) {
            if (patient.getPatientID().equals(patientId)) {
                System.out.println("Accessing record for patient: " + patientId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePatientRecord(String patientId, String recordDetails) {
        for (Patient patient : getAllPatients()) {
            if (patient.getPatientID().equals(patientId)) {
                patient.updateMedicalRecord(recordDetails);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean logRecordAccess(String userId, String patientId, boolean success) {
        String status;
        if (success) {
            status = "Success";
        } else {
            status = "Failure";
        }
        System.out.println("User " + userId + " tried to access patient " + patientId + ": " + status);
        return true;
    }

    // Other getters and setters can be added here
}