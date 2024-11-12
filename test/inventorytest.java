import java.util.List;

public class inventorytest {

    public static void main(String[] args) {

        System.out.println("=== Initial Inventory State ===");
        // Reinitialize initialItems so it can be used throughout the main method
        List<Inventory> initialItems = Inventory.getAllItems();
        for (Inventory item : initialItems) {
            System.out.println("Item: " + item.itemName + ", Quantity: " + item.quantity);
        }

        System.out.println("\n=== Check Inventory Levels ===");
        if (!initialItems.isEmpty()) {
            boolean levelCheck = initialItems.get(0).checkInventoryLevels();
            System.out.println("Global inventory level check: " + (levelCheck ? "At least one item has low stock" : "All stocks are sufficient"));
        }

        System.out.println("\n=== Notify Low Stock ===");
        if (!initialItems.isEmpty()) {
            boolean notifyResult = initialItems.get(0).notifyLowStock(initialItems.get(0).itemName, 10);
            System.out.println("Notification sent for " + initialItems.get(0).itemName + ": " + (notifyResult ? "Success" : "Failure"));
        }

        System.out.println("\n=== Place Stock Order ===");
        if (!initialItems.isEmpty()) {
            boolean orderResult = initialItems.get(0).placeStockOrder(initialItems.get(0).itemName, 100);
            System.out.println("Order placed for " + initialItems.get(0).itemName + ": " + (orderResult ? "Success" : "Failure"));
        }

        System.out.println("\n=== Receive Stock ===");
        if (!initialItems.isEmpty()) {
            boolean receiveResult = initialItems.get(0).receiveStock("Order123");
            System.out.println("Stock received for Order123: " + (receiveResult ? "Success" : "Failure"));
        }

        System.out.println("\n=== Log Stock Activity ===");
        if (!initialItems.isEmpty()) {
            boolean logResult = initialItems.get(0).logStockActivity(initialItems.get(0).itemName, "Received 100 units");
            System.out.println("Activity logged for " + initialItems.get(0).itemName + ": " + (logResult ? "Success" : "Failure"));
        }

        System.out.println("\n=== Update Inventory ===");
        if (!initialItems.isEmpty()) {
            String updateResult = Inventory.updateInventory(initialItems.get(0).itemName, 150);
            System.out.println("Inventory updated for " + initialItems.get(0).itemName + ": " + updateResult);
        }

        System.out.println("\n=== Updated Inventory State ===");
        List<Inventory> updatedItems = Inventory.getAllItems();
        for (Inventory item : updatedItems) {
            System.out.println("Item: " + item.itemName + ", Quantity: " + item.quantity);
        }
    }
}
