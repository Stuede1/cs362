import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Yunpeng Lyu
*
* Code for BloodDonationManagement
*
*/

public class BloodDonationManagement {
    private static final String BLOOD_FILE = "files/blood_donations.txt";
    private String donationID;
    private String donorName;
    private String bloodType;
    private int donationAmount; // ml



    public BloodDonationManagement(String donationID, String donorName, String bloodType, int donationAmount) {
        this.donationID = donationID;
        this.donorName = donorName;
        this.bloodType = bloodType;
        this.donationAmount = donationAmount;
    }

    public static void initializeBloodInventory() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(BLOOD_FILE));
            if (lines.isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(BLOOD_FILE))) {
                    writer.write("DON1,BloodBankInventory,A,0\n");
                    writer.write("DON2,BloodBankInventory,B,0\n");
                    writer.write("DON3,BloodBankInventory,AB,0\n");
                    writer.write("DON4,BloodBankInventory,O,0\n");
                    writer.write("DON5,BloodBankInventory,Others,0\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error initializing blood inventory file: " + e.getMessage());
        }
    }

    public static void registerBloodDonation(BloodDonationManagement donation) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BLOOD_FILE, true))) {
            String data = String.format("%s,%s,%s,%d\n",
                    donation.getDonationID(), donation.getDonorName(), donation.getBloodType(), donation.getDonationAmount());
            writer.write(data);
        } catch (IOException e) {
            System.err.println("Error registering blood donation: " + e.getMessage());
        }


        updateBloodInventory(donation.getBloodType(), donation.getDonationAmount());
    }

    private static void updateBloodInventory(String bloodType, int donationAmount) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(BLOOD_FILE));
            List<String> updatedLines = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].equals("BloodBankInventory") && parts[2].equalsIgnoreCase(bloodType)) {


                    int currentAmount = Integer.parseInt(parts[3]);
                    parts[3] = String.valueOf(currentAmount + donationAmount);
                    updatedLines.add(String.join(",", parts));
                } else {
                    updatedLines.add(line);
                }
            }




            Files.write(Paths.get(BLOOD_FILE), updatedLines);
        } catch (IOException e) {
            System.err.println("Error updating blood inventory: " + e.getMessage());
        }
    }

    public static List<BloodDonationManagement> getAllDonations() {
        List<BloodDonationManagement> donations = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(BLOOD_FILE));
            for (String line : lines) {
                String[] data = line.split(",");


                if (data.length == 4 && !data[1].equalsIgnoreCase("BloodBankInventory")) {
                    BloodDonationManagement donation = new BloodDonationManagement(
                            data[0], data[1], data[2], Integer.parseInt(data[3]));
                    donations.add(donation);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading blood donation file: " + e.getMessage());
        }
        return donations;
    }



    public static String getBloodInventory() {
        StringBuilder inventory = new StringBuilder("Blood Inventory:\n");
        try {
            List<String> lines = Files.readAllLines(Paths.get(BLOOD_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].equals("BloodBankInventory")) {
                    inventory.append(parts[2]).append(": ").append(parts[3]).append(" ml\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading blood inventory: " + e.getMessage());
        }
        return inventory.toString();
    }

    public static boolean withdrawBlood(String bloodType, int requiredAmount) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(BLOOD_FILE));
            List<String> updatedLines = new ArrayList<>();
            boolean bloodWithdrawn = false;
    
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].equals("BloodBankInventory") && parts[2].equalsIgnoreCase(bloodType)) {
                    int currentAmount = Integer.parseInt(parts[3]);
                    if (currentAmount >= requiredAmount) {



                        parts[3] = String.valueOf(currentAmount - requiredAmount);
                        bloodWithdrawn = true;
                    }
                    updatedLines.add(String.join(",", parts));
                } else {
                    updatedLines.add(line);
                }
            }
    


            Files.write(Paths.get(BLOOD_FILE), updatedLines);
    
            return bloodWithdrawn;
        } catch (IOException e) {
            System.err.println("Error withdrawing blood: " + e.getMessage());
            return false;
        }
    }
    



    public String getDonationID() {
        return donationID;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public int getDonationAmount() {
        return donationAmount;
    }
}
