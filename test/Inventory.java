import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
* @author Yunpeng Lyu
*
* Manages the stocks
*
*/

public class Inventory implements InventoryManagementInterface {
    String itemName;
    int quantity;
    private static final String INVENTORY_FILE = "files\\inventory.txt";

    // private static final String INVENTORY_FILE = "cs362/test/files/inventory.txt";


    public Inventory(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    // Retrieve all items from inventory
public static List<Inventory> getAllItems() {
    List<Inventory> items = new ArrayList<>();
    Path path = Paths.get(INVENTORY_FILE);

    if (!Files.exists(path)) {
        System.out.println("Inventory file not found at: " + path.toAbsolutePath());
        return items;
    }

    try {
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            line = line.trim(); // Trim any leading or trailing whitespace
            if (line.isEmpty()) {
                continue; // Skip empty lines
            }

            String[] data = line.split(",");
            if (data.length < 2) {
                System.out.println("Skipping malformed line: " + line);
                continue; // Skip this iteration if there are not enough parts
            }
            try {
                int quantity = Integer.parseInt(data[1].trim()); // Safely trim and parse the quantity
                items.add(new Inventory(data[0].trim(), quantity));
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity for item " + data[0] + " on line: " + line + "; Error: " + e.getMessage());
            }
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

    @Override
    public boolean checkInventoryLevels() {
        for (Inventory item : getAllItems()) {
            if (item.quantity <= 5) {
                System.out.println("Low stock detected for: " + item.itemName);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean notifyLowStock(String itemId, int requiredQuantity) {
        System.out.println("Notifying for low stock of item: " + itemId + ", Required: " + requiredQuantity);
        return true;
    }

    @Override
    public boolean placeStockOrder(String itemId, int quantity) {
        System.out.println("Placing order for item: " + itemId + ", Quantity: " + quantity);
        return true;
    }

    @Override
    public boolean receiveStock(String orderId) {
        System.out.println("Receiving stock for order: " + orderId);
        return true;
    }

    @Override
    public boolean logStockActivity(String itemId, String activityDescription) {
        System.out.println("Logging activity for item: " + itemId + " - " + activityDescription);
        return true;
    }

}
