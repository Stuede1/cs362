import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
* @author Yunpeng Lyu
*
* Volunteer class
*
*/

public class Volunteer {
    private static final String VOLUNTEER_FILE = "files/volunteers.txt";
    private String volunteerID;
    private String name;
    private double currentScheduledHours;
    private boolean currentWorkCompleted;
    private double totalHoursWorked;
    private boolean isActive;
    private String currentDateTaskDescription;

    public Volunteer(String name) {
        this.name = name;
        this.volunteerID = generateVolunteerID();
        this.isActive = true;
        this.currentWorkCompleted = false;
    }

    private static String generateVolunteerID() {
        return "VOL" + System.currentTimeMillis();
    }

    public String registerVolunteer() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VOLUNTEER_FILE, true))) {
            String volunteerData = String.format("%s,%s,%f,%b,%f,%b,%s\n",
                    this.volunteerID,
                    this.name,
                    this.currentScheduledHours,
                    this.currentWorkCompleted,
                    this.totalHoursWorked,
                    this.isActive,
                    this.currentDateTaskDescription);
            writer.write(volunteerData);
            writer.flush();
            return "Volunteer registered successfully. Volunteer ID: " + this.volunteerID;
        } catch (IOException e) {
            return "Failed to register volunteer: " + e.getMessage();
        }
    }

    public static List<Volunteer> getAllVolunteers() {
        List<Volunteer> volunteers = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(VOLUNTEER_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    Volunteer volunteer = new Volunteer(data[1].trim());
                    volunteer.volunteerID = data[0].trim();
                    volunteer.currentScheduledHours = parseDouble(data[2]);
                    volunteer.currentWorkCompleted = Boolean.parseBoolean(data[3].trim());
                    volunteer.totalHoursWorked = parseDouble(data[4]);
                    volunteer.isActive = Boolean.parseBoolean(data[5].trim());
                    volunteer.currentDateTaskDescription = data[6].trim();
                    volunteers.add(volunteer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading volunteer file: " + e.getMessage());
        }
        return volunteers;
    }

    public static void saveAllVolunteers(List<Volunteer> volunteers) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VOLUNTEER_FILE))) {
            for (Volunteer volunteer : volunteers) {
                writer.write(volunteer.formatVolunteerData());


            }
        }
    }

    private String formatVolunteerData() {
        return String.format("%s,%s,%.2f,%b,%.2f,%b,%s\n",
            this.volunteerID,
            this.name,
            this.currentScheduledHours,
            this.currentWorkCompleted,
            this.totalHoursWorked,
            this.isActive,
            this.currentDateTaskDescription);
    }

    public static Volunteer findVolunteerByID(String volunteerID) {
        List<Volunteer> volunteers = getAllVolunteers();
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getVolunteerID().equals(volunteerID)) {
                return volunteer;
            }
        }
        return null;
    }
    

    private static double parseDouble(String data) {
        return data.isEmpty() ? 0.0 : Double.parseDouble(data);
    }


    

    public String getVolunteerID() {
        return volunteerID;
    }

    public String getName() {
        return name;
    }

    public double getCurrentScheduledHours() {
        return currentScheduledHours;
    }

    public void setCurrentScheduledHours(double hours) {
        this.currentScheduledHours = hours;
        updateVolunteerFile();

    }

    public boolean isCurrentWorkCompleted() {
        return currentWorkCompleted;
    }

    public void setCurrentWorkCompleted(boolean completed) {
        this.currentWorkCompleted = completed;
        updateVolunteerFile();

    }

    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(double hours) {
        this.totalHoursWorked += hours;
        updateVolunteerFile();

    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
        updateVolunteerFile();
    }

    public String getCurrentDateTaskDescription() {
        return currentDateTaskDescription;
    }

    public void setCurrentDateTaskDescription(String description) {
        this.currentDateTaskDescription = description;
        updateVolunteerFile();

    }

    private void updateVolunteerFile() {
        try {
            List<Volunteer> volunteers = getAllVolunteers();
            for (int i = 0; i < volunteers.size(); i++) {
                if (volunteers.get(i).getVolunteerID().equals(this.volunteerID)) {
                    // Update the list with the current object's data
                    volunteers.set(i, this);
                    break;
                }
            }
            saveAllVolunteers(volunteers);
        } catch (IOException e) {
            System.err.println("Error updating volunteer file: " + e.getMessage());
        }
    }
    
}
