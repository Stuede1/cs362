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
* Code for Kitchen
*
*/

class Kitchen implements MealPlanInterface{
    public void prepareMeals(String patientID) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("files/mealPlans.txt"));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data[0].equals(patientID)) {
                    if (Boolean.parseBoolean(data[5])) {
                        System.out.println("Preparing meals based on the following plan:\nPatient ID: " + data[0] + "\nPatient Name: " + data[1] + "\nDietary Needs: " + data[2] + "\nDietary Restrictions: " + data[3]);
                    } else {
                        System.out.println("Meal plan not approved for patient " + patientID + ". Please wait for approval.");
                    }
                    return;
                }
            }
            System.out.println("Patient not found.");
        } catch (IOException e) {
            System.err.println("Failed to prepare meals: " + e.getMessage());
        }
    }

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
    public String createOrUpdateMealPlan(String patientID) {
        throw new UnsupportedOperationException("Unimplemented method 'createOrUpdateMealPlan'");
    }

    @Override
    public void setMealPlanStatus(String patientID, boolean isApproved) {
        throw new UnsupportedOperationException("Unimplemented method 'setMealPlanStatus'");
    }
}
