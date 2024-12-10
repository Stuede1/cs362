import java.util.Scanner;

/**
* @author Yunpeng Lyu
*
* Code for BloodDonationManagement UI
*
*/

public class UIBloodDonationManagement {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        BloodDonationManagement.initializeBloodInventory();

        while (true) {
            System.out.println("\n=== Blood Donation Management System ===");
            System.out.println("1. Register Blood Donation");
            System.out.println("2. View All Donations");
            System.out.println("3. View Blood Inventory");
            System.out.println("4. Withdraw Blood for Patient");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            

            switch (choice) {
                case 1:
                    registerDonation();
                    break;
                case 2:
                    viewAllDonations();
                    break;
                case 3:
                    viewBloodInventory();
                    break;
                case 4:
                    withdrawBloodForPatient();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerDonation() {
        String donorName;
        while (true) {
            System.out.print("Enter Donor Name");
            donorName = scanner.nextLine();
            if (donorName.matches("[a-zA-Z ]+")) {
                break;
            }
            System.out.println("Invalid input. Donor name must contain letters only.");
        }

        String bloodType;
        while (true) {
            System.out.print("Enter Blood Type (A, B, AB, O, Others): ");
            bloodType = scanner.nextLine().toUpperCase();
            if (bloodType.equals("A") || bloodType.equals("B") || bloodType.equals("AB") || bloodType.equals("O") || bloodType.equals("OTHERS")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid blood type (A, B, AB, O, Others).");
        }

        int donationAmount;
        while (true) {
            System.out.print("Enter Donation Amount (ml): ");
            if (scanner.hasNextInt()) {
                donationAmount = scanner.nextInt();
                scanner.nextLine(); 
                if (donationAmount > 0) {
                    break;
                }
                System.out.println("Donation amount must be a positive number.");
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }

        BloodDonationManagement donation = new BloodDonationManagement(
                generateDonationID(), donorName, bloodType, donationAmount
        );
        BloodDonationManagement.registerBloodDonation(donation);
        System.out.println("Blood donation registered successfully!");
    }

    private static void viewAllDonations() {
        System.out.println("\n=== All Blood Donations ===");
        for (BloodDonationManagement donation : BloodDonationManagement.getAllDonations()) {
            System.out.printf(
                    "Donation ID: %s, Donor: %s, Blood Type: %s, Amount: %d ml\n",
                    donation.getDonationID(),
                    donation.getDonorName(),
                    donation.getBloodType(),
                    donation.getDonationAmount()
            );
        }
    }

    private static void viewBloodInventory() {
        System.out.println("\n=== Blood Inventory ===");
        System.out.println(BloodDonationManagement.getBloodInventory());
    }

    private static void withdrawBloodForPatient() {
        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine();
    

        Patient patient = Patient.findPatientByID(patientID);
        if (patient == null) {
            System.out.println("Invalid Patient ID. Operation aborted.");
            return;
        }
        System.out.println("Patient Found: " + patient.getPatientName());
    
        String bloodType;
        while (true) {
            System.out.print("Enter Blood Type Required (A, B, AB, O, Others): ");
            bloodType = scanner.nextLine().toUpperCase();
            if (bloodType.equals("A") || bloodType.equals("B") || bloodType.equals("AB") || bloodType.equals("O") || bloodType.equals("OTHERS")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid blood type (A, B, AB, O, Others).");
        }
    
        int requiredAmount;
        while (true) {
            System.out.print("Enter Amount Required (ml): ");
            if (scanner.hasNextInt()) {
                requiredAmount = scanner.nextInt();
                scanner.nextLine();  
                
                if (requiredAmount > 0) {
                    break;
                }
                System.out.println("Amount must be a positive number.");
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();  
            }
        }
    
        boolean success = BloodDonationManagement.withdrawBlood(bloodType, requiredAmount);
    
        if (success) {
            System.out.printf("%d ml of %s blood successfully withdrawn for patient %s (%s).\n",
                    requiredAmount, bloodType, patient.getPatientName(), patientID);

                    patient.updateMedicalRecord("Blood Withdrawal: " + requiredAmount + " ml of " + bloodType + " blood.");
        } else {
            System.out.printf("Insufficient %s blood available to fulfill the request for patient %s (%s).\n",
                    bloodType, patient.getPatientName(), patientID);
        }
    }
    

    private static String generateDonationID() {
        return "DON" + System.currentTimeMillis();
    }
}
