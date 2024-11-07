import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PatientBilling {

    // Class to hold patient information
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
        br.readLine(); // Skip the header
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
            default:
                throw new IllegalArgumentException("Invalid type of care: " + typeOfCare);
        }

        // Insurance adjustment factors
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
            default:
                throw new IllegalArgumentException("Invalid insurance type: " + patient.insurance);
        }

        // Income adjustment based on sliding scale
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

    public static void main(String[] args) {
        try {
            Map<Integer, Patient> patientData = readPatientData("patients.csv");
            
            int patientID = 1; // Example patientID
            String typeOfCare = "specialist";

            Patient patient = patientData.get(patientID);
            if (patient != null) {
                double bill = calculateBill(patient, typeOfCare);
                System.out.println("The patient's bill is: $" + bill);
            } else {
                System.out.println("Patient not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
