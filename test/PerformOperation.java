/**
* @author Nick Riggio
*
*/
public class PerformOperation {
    private String operationName;
    private String surgeon;
    private Patient patient;
    private boolean isOperationSuccessful;
    private HospitalSystem hospitalSystem;

    
    public PerformOperation(String operationName, String surgeon, Patient patient, HospitalSystem hospitalSystem) {
        this.operationName = operationName;
        this.surgeon = surgeon;
        this.patient = patient;
        this.hospitalSystem = hospitalSystem;
        this.isOperationSuccessful = false;
    }

    // Method to perform the operation
    public void perform() { // to do for next iteration
        // Step 1: Check if the patient has scheduled an appointment or checked in for emergency care
        

        // Step 2: Check if enough staff is available
       
        // Step 3: Perform the operation (simulate operation success)
       
        // Step 4: Log the operation to the hospital system
        
        // Step 5: Bill the patient for the operation
       

        // Step 6: Ensure that operation details are logged correctly
       

        // Step 7: Notify the outcome of the operation
        
    }

    // Getters and Setters
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getSurgeon() {
        return surgeon;
    }

    public void setSurgeon(String surgeon) {
        this.surgeon = surgeon;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isOperationSuccessful() {
        return isOperationSuccessful;
    }

    public void setOperationSuccessful(boolean operationSuccessful) {
        isOperationSuccessful = operationSuccessful;
    }

    public HospitalSystem getHospitalSystem() {
        return hospitalSystem;
    }

    public void setHospitalSystem(HospitalSystem hospitalSystem) {
        this.hospitalSystem = hospitalSystem;
    }
}