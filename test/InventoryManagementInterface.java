public interface InventoryManagementInterface {
    boolean checkInventoryLevels();
    boolean notifyLowStock(String itemId, int requiredQuantity);
    boolean placeStockOrder(String itemId, int quantity);
    boolean receiveStock(String orderId);
    boolean logStockActivity(String itemId, String activityDescription);
}