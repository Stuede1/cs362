import java.util.Scanner;

public class MedicationOrderUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Validate Doctor
        Doctor doctor = null;
        while (doctor == null) {
            System.out.println("Enter Doctor ID: ");
            String doctorID = scanner.nextLine();
            if (!doctorID.isEmpty()) {
                doctor = Doctor.findDoctorByID(doctorID);
            }
            if (doctor == null) {
                System.out.println("Doctor with the entered ID not found. Please try again.");
            }
        }

        // Get Doctor Name if validation is successful
        //String doctorName = doctor.getDoctorName();

        // Validate Patient
        Patient patient = null;
        while (patient == null) {
            System.out.println("Enter Patient ID: ");
            String patientID = scanner.nextLine();
            if (!patientID.isEmpty()) {
                patient = Patient.findPatientByID(patientID);
            }
            if (patient == null) {
                System.out.println("Patient with the entered ID not found. Please try again.");
            }
        }

        // Get Patient Name if validation is successful
        //String patientName = patient.getPatientName();

        // Get Medication details
        String medicationName = "";
        while (medicationName.isEmpty()) {
            System.out.println("Enter Medication Name: ");
            medicationName = scanner.nextLine();
            if (medicationName.isEmpty()) {
                System.out.println("Medication name cannot be empty. Please enter again.");
            }
        }

        String dosage = "";
        while (dosage.isEmpty()) {
            System.out.println("Enter Dosage (e.g., 50mg, 100mg): ");
            dosage = scanner.nextLine();
            if (dosage.isEmpty()) {
                System.out.println("Dosage cannot be empty. Please enter again.");
            }
        }

        String frequency = "";
        while (frequency.isEmpty()) {
            System.out.println("Enter Frequency (e.g., Once a day, Twice a day): ");
            frequency = scanner.nextLine();
            if (frequency.isEmpty()) {
                System.out.println("Frequency cannot be empty. Please enter again.");
            }
        }

        // Create an OrderMedication object and place the order
        OrderMedication order = new OrderMedication(doctor.getDoctorID(), doctor.getDoctorName(),
                patient.getPatientID(), patient.getPatientName(), medicationName, dosage, frequency);

        // Print the order details
        order.printOrderDetails();

        // Place the order and save it
        String result = order.orderMedication();
        System.out.println(result);

        // Close the scanner
        scanner.close();
    }
}
