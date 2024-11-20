public class Room {
    private String roomId;
    private String status;
    private String roomType;

    // Constructor
    public Room(String roomId, String status, String roomType) {
        this.roomId = roomId;
        this.status = status;
        this.roomType = roomType;
    }

    // Getter for room ID
    public String getRoomId() {
        return roomId;
    }

    // Getter for room status
    public String getStatus() {
        return status;
    }

    // Getter for room type
    public String getRoomType() {
        return roomType;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }
}