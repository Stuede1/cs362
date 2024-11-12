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
        if (!pdExist(patientId, doctorId)) {
            System.out.println("Patient or doctor not found in the hospital system");
            return;
        }
    
        // Check if the patient has scheduled an appointment and is due for a checkup
        if (!checkAppointment(patientId)) {
            System.out.println("Patient has no scheduled appointments");
            return;
        }
    
        // Perform the checkup
        boolean issuesFound = performHealthCheck(patientId);
        System.out.println("Checkup completed for patient: " + patientId);
    
        // Bill the patient for the checkup
        billPatient(patientId);
    
        // Schedule follow-up if health issues were found
        if (issuesFound && needsFollowUp(patientId)) {
            //schedule follow up
            scheduleFollowUp(patientId, doctorId);
        }
    }
    
    private boolean performHealthCheck(String patientId) {
        // Simulate health check process
        System.out.println("Performing health check for patient: " + patientId);
        // Assume we find some health issues
        return true;  // Return true to simulate issues found
    }
    
    private boolean needsFollowUp(String patientId) {
        // Simulate a check if the patient needs a follow-up
        return false;
    }
    
    private boolean pdExist(String patientId, String doctorId) {
        // Check hospital system for the doctor and patient by ID
        return hospitalSystem.getPatient(patientId) != null && hospitalSystem.getDoctor(doctorId) != null;
    }
    
    private boolean checkAppointment(String patientId) {
        // Check if patient has an upcoming appointment in the hospital system
        // return hospitalSystem.hasAppointment(patientId);
        return true;
    }
    
    private void billPatient(String patientId) {
        // Simulate billing process for the patient
        System.out.println("Billing patient: " + patientId);
        // hospitalSystem.billPatient(patientId);
    }
    
    private void scheduleFollowUp(String patientId, String doctorId) {
        // Schedule a follow-up appointment in the hospital system
        System.out.println("Scheduling follow-up appointment for patient: " + patientId);
        // hospitalSystem.scheduleAppointment(patientId, doctorId);
    }
}
//test
