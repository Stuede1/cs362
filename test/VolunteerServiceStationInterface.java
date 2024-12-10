public interface VolunteerServiceStationInterface {
    String checkInVolunteer(String volunteerID);
    String checkOutVolunteer(String volunteerID);
    String extendVolunteerHours(String volunteerID);
}
