/**
* @author Cole Stuedeman
*
* Student is the Information Expert that knows about Grades.
*
*/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HospitalSystem {
// This is where we add all the actors in our system
    private Map<String, Patient> patients;
    private Map<String, Doctor> doctors;
    private Map<String, Nurse> nurses;
    //private Map<String, Receptionist> receptionists;
    private Map<String, Appointment> appointments;
    // private Map<String, Surgeon> surgeon;
    // private Map<String, Staff> staff;

    public HospitalSystem() {
        patients = new HashMap<>();
        doctors = new HashMap<>();
        nurses = new HashMap<>();
        //receptionists = new HashMap<>();
        appointments = new HashMap<>();
    }

    // public void addReceptionist(Receptionist receptionist) {
    //     receptionists.put(receptionist.getId(), receptionist);
    // }

    // Getters for actors

    
    public Patient getPatient(String patientId) {
        return patients.get(patientId);
    }

    public Doctor getDoctor(String doctorId) {
        return doctors.get(doctorId);
    }

    public Nurse getNurse(String nurseId) {
        return nurses.get(nurseId);
    }
    private static final String ORDER_FILE = "cs362\\test\\files\\orders.txt";

    // Method to place an order
    public boolean placeOrder(MedicationOrder order) {
        // Step 1: Validate the order
        if (order == null || !isValidOrder(order)) {
            System.out.println("Order is invalid.");
            return false;
        }

        // Step 2: Save the order details to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE, true))) {
            writer.write(order.toString()); // Assuming Order class has a proper toString() method
            writer.newLine();
            System.out.println("Order placed successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("Failed to place order: " + e.getMessage());
            return false;
        }
    }

    // A simple validation check for the order
    private boolean isValidOrder() {
        return true;
    //     // Example checks: the order should have a valid patient ID, doctor ID, and medication
    //     return order.getPatientID() != null && !order.getPatientID().isEmpty()
    //             && order.getDoctorID() != null && !order.getDoctorID().isEmpty()
    //             && order.getMedication() != null && !order.getMedication().isEmpty()
    //             && order.getDosage() > 0;
    }
}

    // public Receptionist getReceptionist(String receptionistId) {
    //     return receptionists.get(receptionistId);
    // }