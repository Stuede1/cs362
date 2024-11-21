// public class Driver {
//     private int id;
//     private String name;
//     private boolean available;

//     public Driver(int id, String name, boolean available) {
//         this.id = id;
//         this.name = name;
//         this.available = available;
//     }

//     public int getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public boolean isAvailable() {
//         return available;
//     }

//     public void setAvailable(boolean available) {
//         this.available = available;
//     }

//     @Override
//     public String toString() {
//         return id + ", " + name + ", " + (available ? "Available" : "Unavailable");
//     }
// }
public class Driver {

    private int id;
    private String name;
    private boolean available;
    private String assignedAmbulance;

    public Driver(int id, String name, boolean available, String assignedAmbulance) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.assignedAmbulance = assignedAmbulance != null ? "Ambulance " + assignedAmbulance : "None";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAssignedAmbulance() {
        return assignedAmbulance;
    }

    public void setAssignedAmbulance(String assignedAmbulance) {
        this.assignedAmbulance = assignedAmbulance != null ? "Ambulance " + assignedAmbulance : "None";
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + (available ? "Available" : "Unavailable") + ", " + assignedAmbulance;
    }
}