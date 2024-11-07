/**
* @author Nick Riggio
*
* Student is the Information Expert that knows about Grades.
*
*/
public class PerformCheckup {

    private HospitalSystem hospitalSystem;

    public PerformCheckup(HospitalSystem hospitalSystem) {
        this.hospitalSystem = hospitalSystem;
    }

    public void performCheckup(String patientId, String doctorId) {
       
         //ToDo:
        // Check if patient and doctor exist
        
        //ToDo:

        // Check if the patient has scheduled an appointment and is due for a checkup
    
        // Perform the checkup
        

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

}