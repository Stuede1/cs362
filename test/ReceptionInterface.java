/**
* @author Yunpeng Lyu
*
* Interface for Reception
*
*/

public interface ReceptionInterface {
    String checkInVisitor(String visitorID, String patientID);
    String checkOutVisitor(String visitorID);
    String extendVisitorTime(String visitorID);
}
