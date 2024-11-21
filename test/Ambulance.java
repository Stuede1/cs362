public class Ambulance {
    private String id;
    private boolean available;
    private String assignedPatient;

    public Ambulance(String id, boolean available, String assignedPatient) {
        this.id = id;
        this.available = available;
        this.assignedPatient = assignedPatient;
    }

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
        this.assignedPatient = assignedPatient;
    }

    @Override
    public String toString() {
        return id + ", " + 
               (available ? "Available" : "Unavailable") + ", " +
               (assignedPatient.equals("None") ? "None" : assignedPatient);
    }
}