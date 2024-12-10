import java.time.LocalDateTime;

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
