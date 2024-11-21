import java.time.LocalDateTime;

/**
* @author Yunpeng Lyu
*
* Interface for Visitor
*
*/

public interface VisitorInterface {
    String getVisitorID();
    String getName();
    String getVisitingPatientID();
    LocalDateTime getCheckInTime();
    LocalDateTime getCheckOutTime();
    void setCheckInTime(LocalDateTime checkInTime);
    void setCheckOutTime(LocalDateTime checkOutTime);
    void checkInVisitor();
    void checkOutVisitor();
    void extendVisitTime();
    String registerVisitor();
}
