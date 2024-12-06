import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
* @author Yunpeng Lyu
*
* Code for Dietitian
*
*/

class Dietitian implements MealPlanInterface{
    public String createOrUpdateMealPlan(String patientID) {
        List<String> updatedLines = new ArrayList<>();
        boolean patientFound = false;

        try {
            List<String> lines = Files.readAllLines(Paths.get("files/mealPlans.txt"));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data[0].equals(patientID)) {
                    patientFound = true;
                    String mealPlan = "Meal Plan for Patient: " + data[1] + "\nDietary Needs: " + data[2] + "\nDietary Restrictions: " + data[3];
                    System.out.println(mealPlan);
                    updatedLines.add(String.format("%s,%s,%s,%s,%s,false", data[0], data[1], data[2], data[3], data[4]));
                } else {
                    updatedLines.add(line);
                }
            }

            if (!patientFound) {
                return "Patient not found.";
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/mealPlans.txt"))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
            return "Meal plan updated for Patient ID: " + patientID;
        } catch (IOException e) {
            return "Failed to create or update meal plan: " + e.getMessage();
        }
    }

    public void setMealPlanStatus(String patientID, boolean isApproved) {
        List<String> updatedLines = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("files/mealPlans.txt"));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data[0].equals(patientID)) {
                    updatedLines.add(String.format("%s,%s,%s,%s,%s,%b", data[0], data[1], data[2], data[3], data[4], isApproved));
                } else {
                    updatedLines.add(line);
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/mealPlans.txt"))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to update meal plan status: " + e.getMessage());
        }
    }

    @Override
    public boolean isMealPlanApproved(String patientID) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("files/mealPlans.txt"));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data[0].equals(patientID)) {
                    return Boolean.parseBoolean(data[5]);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to check meal plan approval: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void prepareMeals(String patientID) {
        throw new UnsupportedOperationException("Unimplemented method 'prepareMeals'");
    }
}