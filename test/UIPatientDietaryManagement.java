import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
* @author Yunpeng Lyu
*
* Code for Dietary UI
*
*/

public class UIPatientDietaryManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String BASE_PATH = "files/";

    public static void main(String[] args) {
        try {
            runDietaryManagementSystem();
        } finally {
            scanner.close();
        }
    }

    private static void runDietaryManagementSystem() {
        while (true) {
            System.out.println("\n=== Hospital Patient Dietary Management System ===");
            System.out.println("1. Nurse Access");
            System.out.println("2. Dietitian Access");
            System.out.println("3. Kitchen Access");
            System.out.println("4. Exit");
            System.out.print("Select your role: ");
            int role = scanner.nextInt();
            scanner.nextLine(); 

            switch (role) {
                case 1:
                    nurseAccess();
                    break;
                case 2:
                    dietitianAccess();
                    break;
                case 3:
                    kitchenAccess();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void nurseAccess() {
        System.out.println("\n=== Nurse Access ===");
        System.out.print("Enter Nurse ID: ");
        String nurseID = scanner.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter dietary needs for the patient: ");
        String dietaryNeeds = scanner.nextLine();
        System.out.print("Enter dietary restrictions for the patient: ");
        String dietaryRestrictions = scanner.nextLine();
        System.out.print("Enter patient's current room: ");
        String currentRoom = scanner.nextLine();

        Nurse.designDietaryNeedsFromUI(nurseID, patientID, patientName, dietaryNeeds, dietaryRestrictions, currentRoom);
        System.out.println("Dietary needs for patient " + patientID + " have been designed and communicated to the dietitian. Meal plan status set to 'false'.");
    }

    private static void dietitianAccess() {
        System.out.println("\n=== Dietitian Access ===");
        System.out.print("Enter Patient ID to create or approve meal plan: ");
        String patientID = scanner.nextLine();

        Dietitian dietitian = new Dietitian();
        String mealPlan = dietitian.createOrUpdateMealPlan(patientID);
        if (!mealPlan.equals("Patient not found.")) {
            System.out.print("Do you approve the meal plan? (yes/no): ");
            String approval = scanner.nextLine();
            boolean isApproved = approval.equalsIgnoreCase("yes");
            dietitian.setMealPlanStatus(patientID, isApproved);

            if (isApproved) {
                System.out.println("Meal plan for patient " + patientID + " has been approved.");
            } else {
                System.out.println("Meal plan for patient " + patientID + " remains unapproved.");
            }
        } else {
            System.out.println(mealPlan);
        }
    }

    private static void kitchenAccess() {
        System.out.println("\n=== Kitchen Access ===");
        System.out.print("Enter Patient ID to check meal plan status: ");
        String patientID = scanner.nextLine();

        Kitchen kitchen = new Kitchen();
        if (kitchen.isMealPlanApproved(patientID)) {
            kitchen.prepareMeals(patientID);
            System.out.println("Meals have been prepared and delivered to the patient.");
        } else {
            System.out.println("Meal plan for patient " + patientID + " is not approved yet. Please wait for approval.");
        }
    }
}
