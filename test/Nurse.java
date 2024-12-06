import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class Nurse {
    private String nurseID;
    private String nurseName;

    // File path for storing nurse records
    private static final String NURSE_FILE = "files/nurses.txt";
    private static final String MEAL_PLAN_FILE = "files/mealPlans.txt";

    public Nurse(String nurseID, String nurseName) {
        this.nurseID = nurseID;
        this.nurseName = nurseName;
    }

    public String getNurseID() {
        return nurseID;
    }

    public String getNurseName() {
        return nurseName;
    }

    public String saveNurse() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NURSE_FILE, true))) {
            writer.write(nurseID + "," + nurseName);
            writer.newLine();
            return "Nurse saved successfully. Nurse ID: " + nurseID + " Name: " + nurseName;
        } catch (IOException e) {
            return "Failed to save nurse: " + e.getMessage();
        }
    }

    public static List<Nurse> getAllNurses() {
        List<Nurse> nurses = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(NURSE_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    Nurse nurse = new Nurse(data[0], data[1]);
                    nurses.add(nurse);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading nurse file: " + e.getMessage());
        }
        return nurses;
    }

    public static Nurse findNurseByID(String nurseID) {
        for (Nurse nurse : getAllNurses()) {
            if (nurse.getNurseID().equals(nurseID)) {
                return nurse;
            }
        }
        return null; // Return null if nurse is not found
    }

    public String assignToPatient(String patientID) {
        return "Nurse " + nurseName + " (ID: " + nurseID + ") assigned to patient " + patientID;
    }

    public void designDietaryNeeds(String patientID, String patientName, String dietaryNeeds, String dietaryRestrictions, String currentRoom) {
        List<String> updatedLines = new ArrayList<>();
        boolean patientFound = false;

        try {
            List<String> lines = Files.readAllLines(Paths.get(MEAL_PLAN_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data[0].equals(patientID)) {
                    patientFound = true;
                    updatedLines.add(String.format("%s,%s,%s,%s,%s,false", patientID, patientName, dietaryNeeds, dietaryRestrictions, currentRoom));
                } else {
                    updatedLines.add(line);
                }
            }

            if (!patientFound) {
                updatedLines.add(String.format("%s,%s,%s,%s,%s,false", patientID, patientName, dietaryNeeds, dietaryRestrictions, currentRoom));
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEAL_PLAN_FILE))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
            System.out.println("Nurse " + this.nurseName + " designed dietary needs for patient " + patientName);
        } catch (IOException e) {
            System.err.println("Failed to update meal plan: " + e.getMessage());
        }
    }

    // New method to design dietary needs from UI
    public static void designDietaryNeedsFromUI(String nurseID, String patientID, String patientName, String dietaryNeeds, String dietaryRestrictions, String currentRoom) {
        Nurse nurse = findNurseByID(nurseID);
        if (nurse != null) {
            nurse.designDietaryNeeds(patientID, patientName, dietaryNeeds, dietaryRestrictions, currentRoom);
        } else {
            System.out.println("Nurse not found. Please provide a valid nurse ID.");
        }
    }
}
