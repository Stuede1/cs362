
public interface PatientRecordInterface {
    boolean accessPatientRecord(String patientId);
    boolean updatePatientRecord(String patientId, String recordDetails);
    boolean logRecordAccess(String userId, String patientId, boolean success);
}