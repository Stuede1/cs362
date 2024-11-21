import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
* @author Yunpeng Lyu
*
* Code for reception
*
*/

public class Reception {
    private static final String RECEPTION_LOG_FILE = "files/receptionLogs.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    
    public static String checkInVisitor(String visitorID, String patientID) {
        Visitor visitor = Visitor.findVisitorByID(visitorID);
        if (visitor == null) {
            return "Check-in failed: Visitor not found.";
        }
        if (!visitor.getVisitingPatientID().equals(patientID)) {
            return "Check-in failed: Visitor is not registered to visit patient " + patientID;
        }
    
        Patient patient = Patient.findPatientByID(patientID);
        if (patient == null) {
            return "Check-in failed: Patient not found.";
        }
    
        visitor.checkInVisitor(); 
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECEPTION_LOG_FILE, true))) {
            String details = String.format("Visitor %s checked in to see Patient %s in room %s at %s, Estimated check-out: %s",
                                           visitorID, patientID, patient.getCurrentRoom(), 
                                           visitor.getCheckInTime().format(formatter), 
                                           visitor.getCheckOutTime().format(formatter));
            writer.write(details);
            writer.newLine();
            return "Check-in successful: " + details;
        } catch (IOException e) {
            return "Failed to check in visitor: " + e.getMessage();
        }
    }
    
    public static String checkOutVisitor(String visitorID) {
        Visitor visitor = Visitor.findVisitorByID(visitorID);
        if (visitor == null) {
            return "Check-out failed: Visitor not found.";
        }
    
        visitor.checkOutVisitor();
    
        return "Check-out successful for visitor " + visitorID;
    }
    



    public static String extendVisitorTime(String visitorID) {
        Visitor visitor = Visitor.findVisitorByID(visitorID);
        if (visitor == null) {
            return "Extension failed: Visitor not found.";
        }
    
        if (visitor.getCheckOutTime() == null) {
            return "Extension failed: No existing check-out time.";
        }
    



    
        visitor.extendVisitTime();

        return "Extend successful for visitor " + visitorID;
    }
    
}