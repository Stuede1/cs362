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
    private static final String NURSE_FILE = "cs362\\test\\files\\nurses.txt";

    // Constructor
    public Nurse(String nurseID, String nurseName) {
        this.nurseID = nurseID;
        this.nurseName = nurseName;
    }

    // Getters
    public String getNurseID() {
        return nurseID;
    }

    public String getNurseName() {
        return nurseName;
    }

    // Save nurse to the file
    public String saveNurse() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NURSE_FILE, true))) {
            writer.write(nurseID + "," + nurseName);
            writer.newLine();
            return "Nurse saved successfully. Nurse ID: " + nurseID + " Name: " + nurseName;
        } catch (IOException e) {
            return "Failed to save nurse: " + e.getMessage();
        }
    }

    // Retrieve all nurses from the file
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

    // Find a nurse by ID from the file
    public static Nurse findNurseByID(String nurseID) {
        for (Nurse nurse : getAllNurses()) {
            if (nurse.getNurseID().equals(nurseID)) {
                return nurse;
            }
        }
        return null; // Return null if nurse is not found
    }

    // Assign a nurse to a patient or medication order
    public String assignToPatient(String patientID) {
        return "Nurse " + nurseName + " (ID: " + nurseID + ") assigned to patient " + patientID;
    }
}
