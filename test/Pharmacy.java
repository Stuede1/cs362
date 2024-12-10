import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pharmacy {
    private String inventoryFile;

    public Pharmacy(String inventoryFile) {
        this.inventoryFile = inventoryFile;
    }

    public String getInventoryFile() {
        return inventoryFile;
    }

    public void setInventoryFile(String inventoryFile) {
        this.inventoryFile = inventoryFile;
    }

    public void saveInventory(List<Medicine> medicines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inventoryFile))) {
            for (Medicine medicine : medicines) {
                writer.write(medicine.getName() + ", " + medicine.getBrand() + ", " 
                             + medicine.getQuantity() + ", " + medicine.isInsuranceCovered());
                writer.newLine();
            }
            writer.flush(); // Force write to disk
            System.out.println("Inventory file updated successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory file: " + e.getMessage());
        }
    }
    public List<Medicine> loadInventory() {
        List<Medicine> medicines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inventoryFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0].trim();  // Swapped position
                String brand = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    boolean insuranceCovered = Boolean.parseBoolean(parts[3].trim());
                    medicines.add(new Medicine(brand, name, quantity, insuranceCovered));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
        return medicines;
    }

    // Inner class for Medicine
    public static class Medicine {
        private String brand;
        private String name;
        private int quantity;
        private boolean insuranceCovered;

        public Medicine(String brand, String name, int quantity, boolean insuranceCovered) {
            this.brand = brand;
            this.name = name;
            this.quantity = quantity;
            this.insuranceCovered = insuranceCovered;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public boolean isInsuranceCovered() {
            return insuranceCovered;
        }

        public void setInsuranceCovered(boolean insuranceCovered) {
            this.insuranceCovered = insuranceCovered;
        }

        @Override
        public String toString() {
            return String.format("%-20s %-20s %-10d %-15s", brand, name, quantity, insuranceCovered ? "Yes" : "No");
        }

    }public void displayInventory(List<Medicine> medicines) {
        if (medicines.isEmpty()) {
            System.out.println("No medicines available in the inventory.");
        } else {
            System.out.println("Pharmacy Inventory:");
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-10s %-20s\n", "Brand", "Name", "Quantity", "Insurance Covered");
            System.out.println("------------------------------------------------------------");

            for (Medicine medicine : medicines) {
                System.out.println(medicine);
            }

            System.out.println("------------------------------------------------------------");
        }
    }
}