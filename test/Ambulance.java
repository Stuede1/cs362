public class Ambulance {

    private String id;
    private boolean available;
    private String assignedPatient;
    private String assignedCondition;

    public Ambulance(String id, boolean available, String assignedPatient, String assignedCondition) {
        this.id = id;
        this.available = available;
        this.assignedPatient = assignedPatient != null ? assignedPatient : "None";
        this.assignedCondition = assignedCondition != null ? assignedCondition : "None";
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
        return id + ", " + (available ? "Available" : "Unavailable") + ", " +
                assignedPatient + ", " + assignedCondition;
    }
}
