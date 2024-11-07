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


    // Inner class to represent an appointment object
    class Appointment {
        private String appointmentId;
        private String patientId;
        private String doctorId;
        private String dateTime;
        private String location;

        // Constructor
        public Appointment(String appointmentId, String patientId, String doctorId, String dateTime, String location) {
            this.appointmentId = appointmentId;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.dateTime = dateTime;
            this.location = location;
        }

        // Getters and Setters
        public String getAppointmentId() { return appointmentId; }
        public String getPatientId() { return patientId; }
        public String getDoctorId() { return doctorId; }
        public String getDateTime() { return dateTime; }
        public String getLocation() { return location; }

        public void setDateTime(String dateTime) { this.dateTime = dateTime; }
        public void setLocation(String location) { this.location = location; }
    }

}