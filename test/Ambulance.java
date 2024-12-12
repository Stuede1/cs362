public class Ambulance {

    private String id;
    private boolean available;
    private String assignedPatient;
    private String assignedCondition;
    private String eta;  // Estimated Time of Arrival
    private String room; // Room number for the assigned patient

    // Constructor that takes ETA and room as additional parameters
    public Ambulance(String id, boolean available, String assignedPatient, String assignedCondition, String eta, String room) {
        this.id = id;
        this.available = available;
        this.assignedPatient = assignedPatient != null ? assignedPatient : "None";
        this.assignedCondition = assignedCondition != null ? assignedCondition : "None";
        this.eta = eta != null ? eta : "N/A";  // Default to "N/A" if ETA is not provided
        this.room = room != null ? room : "Unknown";  // Default to "Unknown" if room is not provided
    }

    // Getters and setters for the new fields
    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta != null ? eta : "N/A";
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room != null ? room : "Unknown";
    }

    // Existing getters and setters
    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAssignedPatient() {
        return assignedPatient;
    }

    public void setAssignedPatient(String assignedPatient) {
        this.assignedPatient = assignedPatient != null ? assignedPatient : "None";
    }

    public String getAssignedCondition() {
        return assignedCondition;
    }

    public void setAssignedCondition(String assignedCondition) {
        this.assignedCondition = assignedCondition != null ? assignedCondition : "None";
    }

    @Override
    public String toString() {
        return String.format(
            " %s, %s, %s, %s, %s, %s",
            id,
            available ? "Available" : "Unavailable", // Convert boolean to text
            assignedPatient,
            assignedCondition,
            eta,
            room
        );
    }
}
