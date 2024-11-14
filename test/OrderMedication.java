import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OrderMedication {
    private String doctorID;
    private String doctorName;
    private String patientID;
    private String patientName;
    private String medicationName;
    private String dosage;
    private String frequency;

    // File path for saving medication orders
    private static final String MEDICATION_ORDER_FILE = "cs362\\test\\files\\medication.txt";

    // Constructor to initialize the OrderMedication with Doctor, Patient, and Medication details
    public OrderMedication(String doctorID, String doctorName, String patientID, String patientName, String medicationName, String dosage, String frequency) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.patientID = patientID;
        this.patientName = patientName;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
    }

    // Method to order medication and save to file
    public String orderMedication() {
        // Creating the order string
        String orderDetails = doctorID + "," + doctorName + ","
                + patientID + "," + patientName + ","
                + medicationName + "," + dosage + "," + frequency;

        // Save order details to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEDICATION_ORDER_FILE, true))) {
            writer.write(orderDetails);
            writer.newLine();
            return "Medication ordered successfully for patient: " + patientName;
        } catch (IOException e) {
            return "Error ordering medication: " + e.getMessage();
        }
    }

    // Method to print the order details for confirmation
    public void printOrderDetails() {
        System.out.println("Order Details:");
        System.out.println("Doctor: " + doctorName + " (" + doctorID + ")");
        System.out.println("Patient: " + patientName + " (" + patientID + ")");
        System.out.println("Medication: " + medicationName);
        System.out.println("Dosage: " + dosage);
        System.out.println("Frequency: " + frequency);
    }
}
