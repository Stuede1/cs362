/**
* @author Cole Stuedeman
*
* Student is the Information Expert that knows about Grades.
*
*/
public class OrderMedication {

    private Pharmacy pharmacy;
    private Nurse nurse;
    private HospitalSystem hospitalSystem;

    // Constructor to initialize dependencies
    public OrderMedication(Pharmacy pharmacy, Nurse nurse, HospitalSystem hospitalSystem) {
        this.pharmacy = pharmacy;
        this.nurse = nurse;
        this.hospitalSystem = hospitalSystem;
    }

    // Method to handle the medication order process
    public boolean orderMedication(String doctorId, String patientId, String medicationName, double dosage, String frequency, int duration) {
        return false;
    }

    // Handle alternative medication selection
    public boolean handleAlternativeMedication(String doctorId, String patientId, String alternativeMedication, double dosage, String frequency, int duration) {
        System.out.println("Ordering alternative medication...");
        return orderMedication(doctorId, patientId, alternativeMedication, dosage, frequency, duration);
    }

    // Inner class for representing a medication order
    private class MedicationOrder {
        private String patientId;
        private String doctorId;
        private String medicationName;
        private double dosage;
        private String frequency;
        private int duration;

        // Constructor for the MedicationOrder
        public MedicationOrder(String patientId, String doctorId, String medicationName, double dosage, String frequency, int duration) {
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.medicationName = medicationName;
            this.dosage = dosage;
            this.frequency = frequency;
            this.duration = duration;
        }

        // Getter methods for medication order details
        public String getPatientId() {
            return patientId;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public String getMedicationName() {
            return medicationName;
        }

        public double getDosage() {
            return dosage;
        }

        public String getFrequency() {
            return frequency;
        }

        public int getDuration() {
            return duration;
        }
    }
}