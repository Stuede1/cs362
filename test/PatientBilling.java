import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*
 * Author: Joe Faith
 * 
 * class description:
 * PatientBilling will take the type of care that a patient recieves 
 * and cross references that with the patients income and insurance
 * in order to find the total that the patient should be charged
 * 
 */
public class PatientBilling {

    static class Patient {
        double income;
        String insurance;

        public Patient(double income, String insurance) {
            this.income = income;
            this.insurance = insurance;
        }
    }

    // Method to read patient data from CSV file
    public static Map<Integer, Patient> readPatientData(String fileName) throws IOException {
        Map<Integer, Patient> patientData = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            int patientID = Integer.parseInt(values[0]);
            double income = Double.parseDouble(values[1]);
            String insurance = values[2];
            patientData.put(patientID, new Patient(income, insurance));
        }
        br.close();
        return patientData;
    }

    // Method to calculate bill based on patient data
    public static double calculateBill(Patient patient, String typeOfCare) {
        // Base rates for different types of care
        double baseRate;
        switch (typeOfCare.toLowerCase()) {
            case "primary":
                baseRate = 100.0;
                break;
            case "specialist":
                baseRate = 200.0;
                break;
            case "emergency":
                baseRate = 500.0;
                break;
            case "probono":
                baseRate = 0.0;
            default:
                throw new IllegalArgumentException("Invalid type of care: " + typeOfCare);
        }

        // Insurance adjustment
        double insuranceFactor;
        switch (patient.insurance.toLowerCase()) {
            case "none":
                insuranceFactor = 1.0;
                break;
            case "basic":
                insuranceFactor = 0.8;
                break;
            case "premium":
                insuranceFactor = 0.5;
                break;
            case "probono":
                insuranceFactor = 0.0;
            default:
                throw new IllegalArgumentException("Invalid insurance type: " + patient.insurance);
        }

        // Income adjustment
        double incomeAdjustment;
        if (patient.income < 20000) {
            incomeAdjustment = 0.5;
        } else if (patient.income < 50000) {
            incomeAdjustment = 0.7;
        } else {
            incomeAdjustment = 1.0;
        }

        // Calculate final bill
        double finalBill = baseRate * insuranceFactor * incomeAdjustment;
        return finalBill;
    }
}
