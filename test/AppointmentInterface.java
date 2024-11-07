public interface AppointmentInterface {

    // Method to check if a patient is registered in the system
    boolean isPatientRegistered(String patientId);

    // Method to register a new patient
    void registerPatient(String patientId, String patientName, String contactInfo);

    // Method to check a doctor's availability for a specific date and time
    boolean isDoctorAvailable(String doctorId, String dateTime);

    // Method to schedule a new appointment
    Appointment scheduleAppointment(String patientId, String doctorId, String dateTime);

    // Method to reschedule an existing appointment
    Appointment rescheduleAppointment(String appointmentId, String newDateTime);

    // Method to cancel an appointment
    boolean cancelAppointment(String appointmentId);

}