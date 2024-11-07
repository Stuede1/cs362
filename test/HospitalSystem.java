/**
* @author Cole Stuedeman
*
* Student is the Information Expert that knows about Grades.
*
*/
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
}

    // public Receptionist getReceptionist(String receptionistId) {
    //     return receptionists.get(receptionistId);
    // }