
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        // Testing patient registration
        System.out.println("=== Patient Registration Tests ===");
        Patient patient1 = new Patient("John Doe", "1985-03-21", "123 Elm St");
        String registrationResult1 = patient1.registerPatient();
        System.out.println(registrationResult1 + " Patient Name: " + patient1.patientName);

        Patient patient2 = new Patient("Jane Smith", "1990-07-15", "456 Oak St");
        String registrationResult2 = patient2.registerPatient();
        System.out.println(registrationResult2 + " Patient Name: " + patient2.patientName);


        // Trying to add patient with no name
        Patient patient3 = new Patient("", "2000-05-10", "789 Pine St"); // Missing name
        String registrationResult3 = patient3.registerPatient();
        System.out.println(registrationResult3 + " Patient Name" + patient3.patientName);

        // Testing retrieval of all patients, skipping because not all feilds have been implimented in this iteration
        System.out.println("\n=== Retrieve All Patients ===");
        List<Patient> allPatients = Patient.getAllPatients();
        for (Patient p : allPatients) {
            System.out.println("Patient ID: " + p.getPatientID() + ", Name: " + p.patientName);
        }

        // Testing access to a patient record
        System.out.println("\n=== Access Patient Record ===");
        boolean accessResult = patient1.accessPatientRecord(patient1.getPatientID());
        System.out.println("Access patient " + patient1.getPatientID() + ": " + (accessResult ? "Success " + patient1.patientName : "Failure"));

        // Testing medical record updates
        System.out.println("\n=== Update Patient Medical Record ===");
        String updateResult1 = patient1.updateMedicalRecord("Diagnosed with seasonal allergies.");
        System.out.println(updateResult1);
        String updateResult2 = patient2.updateMedicalRecord("Prescribed antihistamines.");
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
        boolean logResult = patient1.logRecordAccess("John", patient1.getPatientID(), accessResult);
        System.out.println("Log record access: " + (logResult ? "Logged" : "Failed to log"));









//         System.out.println("\n=== Test Case 0: Retrieve All Doctors ===");
//         List<Doctor> allDoctors = Doctor.getAllDoctors();
//         for (Doctor doc : allDoctors) {
//             System.out.println("Doctor ID: " + doc.getDoctorID() + ", Name: " + doc.getDoctorName());
//         }


//         // Test Case 1: Save new doctors to file
        
//         System.out.println("=== Test Case 1: Save Doctor ===");
//         Doctor doctor1 = new Doctor("D004", "Dr. David Brown");
//         System.out.println(doctor1.saveDoctor());

//         Doctor doctor2 = new Doctor("D005", "Dr. Emma Wilson");
//         System.out.println(doctor2.saveDoctor());

//         // Test Case 2: Retrieve all doctors
//         System.out.println("\n=== Test Case 2: Retrieve All Doctors after adding ===");
//         List<Doctor> allDoctors2 = Doctor.getAllDoctors();
//         for (Doctor doc : allDoctors2) {
//             System.out.println("Doctor ID: " + doc.getDoctorID() + ", Name: " + doc.getDoctorName());
//         }

//         // Test Case 3: Find a doctor by ID
//         System.out.println("\n=== Test Case 3: Find Doctor by ID ===");
//         String searchID = "D002";
//         Doctor foundDoctor = Doctor.findDoctorByID(searchID);
//         if (foundDoctor != null) {
//             System.out.println("Found Doctor - ID: " + foundDoctor.getDoctorID() + ", Name: " + foundDoctor.getDoctorName());
//         } else {
//             System.out.println("Doctor with ID " + searchID + " not found.");
//         }

//         // Test Case 4: Attempt to find a non-existent doctor
//         System.out.println("\n=== Test Case 4: Find Non-Existent Doctor ===");
//         searchID = "D999";
//         foundDoctor = Doctor.findDoctorByID(searchID);
//         if (foundDoctor != null) {
//             System.out.println("Found Doctor - ID: " + foundDoctor.getDoctorID() + ", Name: " + foundDoctor.getDoctorName());
//         } else {
//             System.out.println("Doctor with ID " + searchID + " not found.");
//         }
    












//     // Test Case 1: Schedule a new appointment
//     Patient patient = new Patient("P001", "John Doe", "555-1234");
//     Doctor doctor = new Doctor("D001", "Dr. Alice Smith");
//     Appointment appointment1 = new Appointment("2024-11-15", "10:00 AM", patient, doctor);

//     System.out.println("=== Test Case 1: Schedule Appointment ===");
//     System.out.println(appointment1.scheduleAppointment());

//     // Test Case 2: Attempt to schedule another appointment at the same time for the same doctor
//     Patient patientt = new Patient("P002", "Jane Doe", "555-5678");
//     Appointment appointment2 = new Appointment("2024-11-15", "10:00 AM", patientt, doctor1);

//     System.out.println("\n=== Test Case 2: Schedule Conflicting Appointment ===");
//     System.out.println(appointment2.scheduleAppointment());

//     // Test Case 3: Retrieve all appointments from the file
//     System.out.println("\n=== Test Case 3: Retrieve All Appointments ===");
//     List<Appointment> allAppointments = Appointment.getAllAppointments();
//     for (Appointment app : allAppointments) {
//         System.out.println("Appointment ID: " + app.appointmentID + ", Date: " + app.date + ", Time: " + app.time +
//                 ", Patient ID: " + app.patient.getPatientID() + ", Doctor ID: " + app.doctor.getDoctorID());
//     }

//     // Test Case 4: Retrieve all appointments for a specific doctor
//     System.out.println("\n=== Test Case 4: Retrieve Appointments for Doctor ===");
//     List<Appointment> doctorAppointments = Appointment.getAllAppointmentsForDoctor(doctor1.getDoctorID());
//     for (Appointment app : doctorAppointments) {
//         System.out.println("Appointment ID: " + app.appointmentID + ", Date: " + app.date + ", Time: " + app.time +
//                 ", Patient ID: " + app.patient.getPatientID() + ", Doctor ID: " + app.doctor.getDoctorID() + " Doctor name: " + doctor1.getDoctorName());
//     }

//     // Test Case 5: Schedule an appointment at a different time (no conflict)
//     Appointment appointment3 = new Appointment("2024-11-15", "11:00 AM", patient1, doctor1);

//     System.out.println("\n=== Test Case 5: Schedule Non-Conflicting Appointment ===");
//     System.out.println(appointment3.scheduleAppointment());

//     // Test Case 6: Verify a non-existent doctor returns no appointments
//     System.out.println("\n=== Test Case 6: Retrieve Appointments for Non-Existent Doctor ===");
//     List<Appointment> nonExistentDoctorAppointments = Appointment.getAllAppointmentsForDoctor("D999");
//     if (nonExistentDoctorAppointments.isEmpty()) {
//         System.out.println("No appointments found for Doctor ID: D999");
//     } else {
//         for (Appointment app : nonExistentDoctorAppointments) {
//             System.out.println("Appointment ID: " + app.appointmentID + ", Date: " + app.date + ", Time: " + app.time +
//                     ", Patient ID: " + app.patient.getPatientID() + ", Doctor ID: " + app.doctor.getDoctorID());
//         }
//     }
}
}
