public class VolunteerServiceStation {

    public static String checkInVolunteer(String volunteerID, String taskDescription) {
        Volunteer volunteer = Volunteer.findVolunteerByID(volunteerID);
        if (volunteer == null) {
            return "Check-in failed: Volunteer not found.";
        }
        volunteer.setCurrentDateTaskDescription(taskDescription);
        volunteer.setCurrentScheduledHours(0);  
        volunteer.setCurrentWorkCompleted(false);
        return "Check-in successful for volunteer " + volunteerID + " with task: " + taskDescription;
    }

    public static String checkOutVolunteer(String volunteerID) {
        Volunteer volunteer = Volunteer.findVolunteerByID(volunteerID);
        if (volunteer == null) {
            return "Check-out failed: Volunteer not found.";
        }
        if (!volunteer.isCurrentWorkCompleted()) {
            volunteer.setTotalHoursWorked(volunteer.getCurrentScheduledHours());
            volunteer.setCurrentWorkCompleted(true);
        }
        return "Check-out successful for volunteer " + volunteerID;
    }

    public static String extendVolunteerHours(String volunteerID, double hours) {
        Volunteer volunteer = Volunteer.findVolunteerByID(volunteerID);
        if (volunteer == null) {
            return "Extension failed: Volunteer not found.";
        }
        volunteer.setCurrentScheduledHours(volunteer.getCurrentScheduledHours() + hours);
        return "Extended hours by " + hours + " for volunteer " + volunteerID;
    }

    public static String toggleActiveStatus(String volunteerID) {
        Volunteer volunteer = Volunteer.findVolunteerByID(volunteerID);
        if (volunteer == null) {
            return "Toggle status failed: Volunteer not found.";
        }
        volunteer.setActive(!volunteer.isActive());
        return "Volunteer " + volunteerID + " is now " + (volunteer.isActive() ? "active" : "inactive");
    }
}
