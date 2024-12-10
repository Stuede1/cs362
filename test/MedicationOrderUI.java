import java.util.List;
import java.util.Scanner;

public class MedicationOrderUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pharmacy pharmacy = new Pharmacy("./files/pharmacy.txt");

        while (true) {
            System.out.println("Welcome to the Pharmacy Management System");
            System.out.println("1. Place a Medication Order");
            System.out.println("2. Display Pharmacy Inventory");
            System.out.println("3. Modify Pharmacy Inventory");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    // Place Medication Order
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

                    String medicationName = "";
                    boolean medicationExists = false;

                    while (!medicationExists) {
                        System.out.println("Enter Medication Name: ");
                        medicationName = scanner.nextLine();

                        List<Pharmacy.Medicine> medicines = pharmacy.loadInventory();
                        for (Pharmacy.Medicine medicine : medicines) {
                            if (medicine.getName().equalsIgnoreCase(medicationName)) {
                                medicationExists = true;
                                break;
                            }
                        }

                        if (!medicationExists) {
                            System.out.println("Medication not found in the inventory. Please enter a valid medication name.");
                        }
                    }

                    System.out.println("Enter Dosage (e.g., 50mg, 100mg): ");
                    String dosage = scanner.nextLine();

                    System.out.println("Enter Frequency (e.g., Once a day, Twice a day): ");
                    String frequency = scanner.nextLine();

                    OrderMedication order = new OrderMedication(
                        doctor.getDoctorID(),
                        doctor.getDoctorName(),
                        patient.getPatientID(),
                        patient.getPatientName(),
                        medicationName,
                        dosage,
                        frequency
                    );

                    order.printOrderDetails();
                    String result = order.orderMedication();
                    System.out.println(result);
                    break;

                case 2:
                    // Display Pharmacy Inventory
                    List<Pharmacy.Medicine> medicines = pharmacy.loadInventory();
                    System.out.printf("%-15s %-20s %-10s %-15s\n", "Brand", "Name", "Quantity", "Insurance Covered");
                    System.out.println("---------------------------------------------------------------");
                    for (Pharmacy.Medicine medicine : medicines) {
                        System.out.printf("%-15s %-20s %-10d %-15s\n",
                                medicine.getBrand(),
                                medicine.getName(),
                                medicine.getQuantity(),
                                medicine.isInsuranceCovered());
                    }
                    break;

                case 3:
                    // Modify Pharmacy Inventory
                    System.out.println("Modify Pharmacy Inventory");
                    System.out.println("Enter Medication Name to Update: ");
                    String medToUpdate = scanner.nextLine();

                    List<Pharmacy.Medicine> inventory = pharmacy.loadInventory();
                    boolean found = false;

                    for (Pharmacy.Medicine medicine : inventory) {
                        if (medicine.getName().equalsIgnoreCase(medToUpdate)) {
                            System.out.println("Enter new quantity: ");
                            int newQuantity = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            medicine.setQuantity(newQuantity);
                            System.out.println("Inventory updated for " + medToUpdate);
                            found = true;
                            break;
                        }
                    }
            
                    if (!found) {
                        System.out.println("Medication not found in the inventory.");
                    } else {
                        pharmacy.saveInventory(inventory); // Save updates to file
                        break;
                    }

                   
                    

                case 4:
                    // Exit
                    System.out.println("Exiting");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
