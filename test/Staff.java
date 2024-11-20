public class Staff {
    private int id;
    private String name;
    private String assignedRoom;

    // Constructor
    public Staff(int id, String name, String assignedRoom) {
        this.id = id;
        this.name = name;
        this.assignedRoom = assignedRoom;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for assigned room, returns "Available" if no room assigned
    public String getAssignedRoom() {
        return (assignedRoom == null || assignedRoom.isEmpty()) ? "Available" : assignedRoom;
    }

    // Setter for assigned room
    public void setAssignedRoom(String assignedRoom) {
        this.assignedRoom = assignedRoom;
    }
}