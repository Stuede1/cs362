
/**
* @author Cole Stuedeman
*
* Student is the Information Expert that knows about Grades.
*
*/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String doctorID;
    private String doctorName;

    private static final String DOCTOR_FILE = "cs362\\test\\files\\doctors.txt"; // File to store doctor records

    public Doctor(String doctorID, String doctorName) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getName() {
        return doctorName;
    }

    // Saves the current doctor's information to doctor.txt
    public String saveDoctor() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTOR_FILE, true))) {
            writer.write(doctorID + "," + doctorName + "\n");
            writer.newLine();
            return "Doctor saved successfully. Doctor ID: " + doctorID;
        } catch (IOException e) {
            return "Failed to save doctor: " + e.getMessage();
        }
    }

    // Reads all doctors from doctor.txt and returns them as a list
    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DOCTOR_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    Doctor doctor = new Doctor(data[0], data[1]);
                    doctors.add(doctor);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading doctor file: " + e.getMessage());
        }
        return doctors;
    }

    // Finds a doctor by ID from the doctor.txt file
    public static Doctor findDoctorByID(String doctorID) {
        for (Doctor doctor : getAllDoctors()) {
            if (doctor.getDoctorID().equals(doctorID)) {
                return doctor;
            }
        }
        return null; // Return null if doctor is not found
    }
}