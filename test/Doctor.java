
/**
* @author Cole Stuedeman
*
* Student is the Information Expert that knows about Grades.
*
*/
public class Doctor {
    private String doctorID;
    private String doctorName;

    public Doctor(String doctorID, String doctorName) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
    }

    public String getDoctorID() {
        return doctorID;
    }

    // Additional getters if needed
    public String getName() {
        return doctorName;
    }
}