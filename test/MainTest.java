
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        // Testing patient registration
        System.out.println("=== Patient Registration Tests ===");
        Patient patient1 = new Patient("John Doe", "1985-03-21", "123 Elm St");
        String registrationResult1 = patient1.registerPatient();
        System.out.println(registrationResult1);

        Patient patient2 = new Patient("Jane Smith", "1990-07-15", "456 Oak St");
        String registrationResult2 = patient2.registerPatient();
        System.out.println(registrationResult2);

        Patient patient3 = new Patient("", "2000-05-10", "789 Pine St"); // Missing name
        String registrationResult3 = patient3.registerPatient();
        System.out.println(registrationResult3);

        // Testing retrieval of all patients
        System.out.println("\n=== Retrieve All Patients ===");
        List<Patient> allPatients = Patient.getAllPatients();
        for (Patient p : allPatients) {
            System.out.println("Patient ID: " + p.getPatientID() + ", Name: " + p.patientName);
        }

        // Testing access to a patient record
        System.out.println("\n=== Access Patient Record ===");
        boolean accessResult = patient1.accessPatientRecord(patient1.getPatientID());
        System.out.println("Access patient " + patient1.getPatientID() + ": " + (accessResult ? "Success" : "Failure"));

        // Testing medical record updates
        System.out.println("\n=== Update Patient Medical Record ===");
        String updateResult1 = patient1.updateMedicalRecord("Diagnosed with seasonal allergies.");
        System.out.println(updateResult1);
        String updateResult2 = patient1.updateMedicalRecord("Prescribed antihistamines.");
        System.out.println(updateResult2);

        // Testing retrieval of patient medical records to ensure updates were saved
        System.out.println("\n=== Verify Patient Medical Records ===");
        List<Patient> updatedPatients = Patient.getAllPatients();
        for (Patient p : updatedPatients) {
            if (p.getPatientID().equals(patient1.getPatientID())) {
                System.out.println("Medical Record for " + p.patientName + ":");
                System.out.println(p.medicalRecord);
            }
        }

        // Testing logging record access attempt
        System.out.println("\n=== Log Record Access Attempt ===");
        boolean logResult = patient1.logRecordAccess("User123", patient1.getPatientID(), accessResult);
        System.out.println("Log record access: " + (logResult ? "Logged" : "Failed to log"));











        // Test Case 1: Save new doctors to file
        Doctor doctor1 = new Doctor("D004", "Dr. David Brown");
        System.out.println("=== Test Case 1: Save Doctor ===");
        System.out.println(doctor1.saveDoctor());

        Doctor doctor2 = new Doctor("D005", "Dr. Emma Wilson");
        System.out.println(doctor2.saveDoctor());

        // Test Case 2: Retrieve all doctors
        System.out.println("\n=== Test Case 2: Retrieve All Doctors ===");
        List<Doctor> allDoctors = Doctor.getAllDoctors();
        for (Doctor doc : allDoctors) {
            System.out.println("Doctor ID: " + doc.getDoctorID() + ", Name: " + doc.getName());
        }

        // Test Case 3: Find a doctor by ID
        System.out.println("\n=== Test Case 3: Find Doctor by ID ===");
        String searchID = "D002";
        Doctor foundDoctor = Doctor.findDoctorByID(searchID);
        if (foundDoctor != null) {
            System.out.println("Found Doctor - ID: " + foundDoctor.getDoctorID() + ", Name: " + foundDoctor.getName());
        } else {
            System.out.println("Doctor with ID " + searchID + " not found.");
        }

        // Test Case 4: Attempt to find a non-existent doctor
        System.out.println("\n=== Test Case 4: Find Non-Existent Doctor ===");
        searchID = "D999";
        foundDoctor = Doctor.findDoctorByID(searchID);
        if (foundDoctor != null) {
            System.out.println("Found Doctor - ID: " + foundDoctor.getDoctorID() + ", Name: " + foundDoctor.getName());
        } else {
            System.out.println("Doctor with ID " + searchID + " not found.");
        }
    }
}