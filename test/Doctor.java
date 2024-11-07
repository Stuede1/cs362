import java.util.HashMap;
import java.util.Map;

/**
* @author Cole Stuedeman
*
* Student is the Information Expert that knows about Grades.
*
*/
public class Doctor {
    private String doctorID;
    private String name;
    private String specialty;

    // File path for storing doctor records
    private static final String DOCTOR_FILE = "doctors.txt";

    public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
        this.doctorID = generateDoctorID();
    }

    private String generateDoctorID() {
        return "D" + (DoctorDatabase.getDoctorCount() + 1);
    }

    // Getters
    public String getDoctorID() {
        return doctorID;
    }

    // Other methods and attributes can be added here
}