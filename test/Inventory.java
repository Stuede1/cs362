import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private String itemName;
    private int quantity;
    private static final String INVENTORY_FILE = "inventory.txt";

    public Inventory(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    // Retrieve all items from inventory
    public static List<Inventory> getAllItems() {
        List<Inventory> items = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(INVENTORY_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                items.add(new Inventory(data[0], Integer.parseInt(data[1])));
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
        return items;
    }

    // Update inventory item
    public static String updateInventory(String itemName, int newQuantity) {
        List<Inventory> items = getAllItems();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Inventory item : items) {
                if (item.itemName.equals(itemName)) {
                    writer.write(itemName + "," + newQuantity);
                } else {
                    writer.write(item.itemName + "," + item.quantity);
                }
                writer.newLine();
            }
            return "Inventory update successful.";
        } catch (IOException e) {
            return "Inventory update failed: " + e.getMessage();
        }
    }

    // Check low stock
    public static void checkLowStock(int limit) {
        List<Inventory> items = getAllItems();
        for (Inventory item : items) {
            if (item.quantity <= limit) {
                System.out.println("Low stock alert: " + item.itemName);
            }
        }
    }
}
