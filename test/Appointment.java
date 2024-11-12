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

public class Appointment implements AppointmentInterface {
    private String appointmentID;
    private String date;
    private String time;
    private Patient patient;
    private Doctor doctor;

    // File path for storing appointment records
    private static final String APPOINTMENT_FILE = "appointments.txt";

    public Appointment(String date, String time, Patient patient, Doctor doctor) {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentID = generateAppointmentID();
    }

    // Generates a unique appointment ID
    private String generateAppointmentID() {
        return "A" + (getAllAppointments().size() + 1);
    }

    public String scheduleAppointment() {
        List<Appointment> appointmentsForDoctor = getAllAppointmentsForDoctor(doctor.getDoctorID());
        
        // Check for time conflicts
        for (Appointment appointment : appointmentsForDoctor) {
            if (appointment.date.equals(this.date) && appointment.time.equals(this.time)) {
                return "Scheduling failed: Doctor is unavailable at this time.";
            }
        }
        
        // If no conflicts, save the appointment
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_FILE, true))) {
            writer.write(appointmentID + "," + date + "," + time + "," + patient.getPatientID() + "," + doctor.getDoctorID());
            writer.newLine();
            return "Appointment scheduled successfully. Appointment ID: " + appointmentID;
        } catch (IOException e) {
            return "Scheduling failed: " + e.getMessage();
        }
    }
    
    // Retrieve all appointments from the file
    public static List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(APPOINTMENT_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                appointments.add(new Appointment(data[1], data[2], new Patient(data[3], "", ""), new Doctor(data[4], ""))); // Load patient and doctor
                appointments.get(appointments.size() - 1).appointmentID = data[0]; // Set the ID for the appointment
            }
        } catch (IOException e) {
            System.out.println("Error reading appointment file: " + e.getMessage());
        }
        return appointments;
    }

    // Get appointments for a specific doctor
    public static List<Appointment> getAllAppointmentsForDoctor(String doctorID) {
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment : getAllAppointments()) {
            if (appointment.doctor.getDoctorID().equals(doctorID)) {
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    @Override
    public boolean isPatientRegistered(String patientId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerPatient(String patientId, String patientName, String contactInfo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isDoctorAvailable(String doctorId, String dateTime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Appointment scheduleAppointment(String patientId, String doctorId, String dateTime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Appointment rescheduleAppointment(String appointmentId, String newDateTime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean cancelAppointment(String appointmentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }




    
}