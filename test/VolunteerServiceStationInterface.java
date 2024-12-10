/**
* @author Yunpeng Lyu
*
* Volunteer Service Station Interface
*
*/
public interface VolunteerServiceStationInterface {
    String checkInVolunteer(String volunteerID);
    String checkOutVolunteer(String volunteerID);
    String extendVolunteerHours(String volunteerID);
}
