import java.time.LocalDateTime;
/**
* @author Yunpeng Lyu
*
* Volunteer Interface
*
*/
public interface VolunteerInterface {
    String getVolunteerID();
    String getName();
    LocalDateTime getCheckInTime();
    LocalDateTime getCheckOutTime();
    void setCheckInTime(LocalDateTime checkInTime);
    void setCheckOutTime(LocalDateTime checkOutTime);
    void checkInVolunteer();
    void checkOutVolunteer();
    void extendVolunteerHours();
    String registerVolunteer();
}
