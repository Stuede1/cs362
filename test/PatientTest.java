import java.util.List;

public class PatientTest {

    public static void main(String[] args) {
        System.out.println("=== Initial Patient State ===");
        List<Patient> initialPatients = Patient.getAllPatients();
        for (Patient patient : initialPatients) {
            System.out.println("Patient ID: " + patient.getPatientID() + ", Name: " + patient.patientName + ", Medical Record: " + patient.medicalRecord);
        }

        System.out.println("\n=== Register New Patients ===");
        // Register a new patient
        Patient newPatient1 = new Patient("Alice Johnson", "1992-08-15", "456 Maple Street");
        System.out.println(newPatient1.registerPatient() + " - Patient ID: " + newPatient1.getPatientID());

        // Register another patient
        Patient newPatient2 = new Patient("Bob Smith", "1984-12-22", "789 Oak Street");
        System.out.println(newPatient2.registerPatient() + " - Patient ID: " + newPatient2.getPatientID());

        System.out.println("\n=== Update Medical Records ===");
        // Update medical records for the first new patient
        if (!initialPatients.isEmpty()) {
            Patient patientToUpdate = initialPatients.get(0);
            patientToUpdate.updateMedicalRecord("Added vaccination record.");
            System.out.println("Updated medical record for " + patientToUpdate.getPatientID());
        }

        System.out.println("\n=== Retrieve Updated Patient Records ===");
        // Retrieve all patients to see updates
        List<Patient> updatedPatients = Patient.getAllPatients();
        for (Patient patient : updatedPatients) {
            System.out.println("Patient ID: " + patient.getPatientID() + ", Name: " + patient.patientName + ", Medical Record: " + patient.medicalRecord);
        }

        System.out.println("\n=== Access Specific Patient Record ===");
        // Access a specific patient's record by ID
        if (!updatedPatients.isEmpty()) {
            Patient patient = updatedPatients.get(0);
            boolean accessResult = patient.accessPatientRecord(patient.getPatientID());
            System.out.println("Access record for " + patient.getPatientID() + ": " + (accessResult ? "Success" : "Failure"));
        }

        System.out.println("\n=== Log Record Access ===");
        // Log access attempt for a specific patient
        if (!updatedPatients.isEmpty()) {
            Patient patient = updatedPatients.get(0);
            boolean logResult = patient.logRecordAccess("AdminUser", patient.getPatientID(), true);
            System.out.println("Log access for " + patient.getPatientID() + ": " + (logResult ? "Logged" : "Failed to log"));
        }
    }
}
