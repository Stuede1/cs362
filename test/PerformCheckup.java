/**
* @author Nick Riggio
*
*/
public class PerformCheckup {

    private HospitalSystem hospitalSystem;

    public PerformCheckup(HospitalSystem hospitalSystem) {
        this.hospitalSystem = hospitalSystem;
    }

    public void performCheckup(String patientId, String doctorId) {
       
        // Check if patient and doctor exist
        pdExist(patientId, doctorId);

        // Check if the patient has scheduled an appointment and is due for a checkup
    
        // Perform the checkup
        performHealthCheck(patientId);

        // Simulate the checkup process

        // Bill the patient for the checkup
       
    }

    private boolean performHealthCheck(Patient patient) {
        // Simulate health check process
        // Assume we find some health issues
        return true;  // Return true to simulate issues found
    }

    private boolean needsFollowUp(Patient patient) {
        // Simulate a check if the patient needs a follow-up
        // For now, we assume the patient needs a follow-up
        return true;
    }

    private boolean pdExist(String patientId, String doctorId) {
        // Check hospital system for the doctor and patient Id
        return true;
    }
}