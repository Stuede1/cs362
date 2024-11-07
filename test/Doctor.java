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
        this.doctorID = doctorID;
    }

    private String generateDoctorID() {
        return doctorID;
    }

    // Getters
    public String getDoctorID() {
        return doctorID;
    }

    // Other methods and attributes can be added here
}