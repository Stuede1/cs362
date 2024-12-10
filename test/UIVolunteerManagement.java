import java.util.List;
import java.util.Scanner;

/**
* @author Yunpeng Lyu
*
* UI for Volunteer 
*
*/

public class UIVolunteerManagement {
    private static List<Volunteer> volunteers = Volunteer.getAllVolunteers();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Hospital Volunteer Management System ===");
            System.out.println("1. Register Volunteer");
            System.out.println("2. Check-in Volunteer");
            System.out.println("3. Check-out Volunteer");
            System.out.println("4. Extend Volunteer Hours");
            System.out.println("5. Deactivate Or Activate Volunteer");
            System.out.println("6. View All Volunteers");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerVolunteer();
                    break;
                case 2:
                    checkInVolunteer();
                    break;
                case 3:
                    checkOutVolunteer();
                    break;
                case 4:
                    extendVolunteerHours();
                    break;
                case 5:
                    toggleActiveStatus();
                    break;
                case 6:
                    viewAllVolunteers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            reloadVolunteers();
        }
    }

    private static void registerVolunteer() {
        System.out.print("Enter Volunteer's Name: ");
        String name = scanner.nextLine();
        Volunteer volunteer = new Volunteer(name);
        String result = volunteer.registerVolunteer();
        System.out.println(result);
    }

    // private static void checkInVolunteer() {
    //     System.out.print("Enter Volunteer ID to check-in: ");
    //     String volunteerID = scanner.nextLine();
    //     System.out.print("Enter Task Description: ");
    //     String taskDescription = scanner.nextLine();
    //     String result = VolunteerServiceStation.checkInVolunteer(volunteerID, taskDescription);
    //     System.out.println(result);
    // }

    private static void checkInVolunteer() {
        System.out.print("Enter Volunteer ID to check-in: ");
        String volunteerID = scanner.nextLine();
        Volunteer volunteer = Volunteer.findVolunteerByID(volunteerID);
    
        if (volunteer == null) {
            System.out.println("Volunteer not found.");
            return;
        }
    
        System.out.print("Enter Task Description: ");
        String taskDescription = scanner.nextLine();
        volunteer.setCurrentDateTaskDescription(taskDescription);
    
        System.out.print("Enter Scheduled Hours: ");
        double scheduledHours = scanner.nextDouble();
        scanner.nextLine(); 
        volunteer.setCurrentScheduledHours(scheduledHours);
    
        System.out.println("Volunteer " + volunteerID + " successfully checked in with task: " +
                taskDescription + " and scheduled hours: " + scheduledHours);
    }

    // private static void checkOutVolunteer() {
    //     System.out.print("Enter Volunteer ID to check-out: ");
    //     String volunteerID = scanner.nextLine();
    //     String result = VolunteerServiceStation.checkOutVolunteer(volunteerID);
    //     System.out.println(result);
    // }

    private static void checkOutVolunteer() {
        System.out.print("Enter Volunteer ID to check-out: ");
        String volunteerID = scanner.nextLine();
    
        String result = VolunteerServiceStation.checkOutVolunteer(volunteerID);
        System.out.println(result);
    
        Volunteer volunteer = Volunteer.findVolunteerByID(volunteerID);
        if (volunteer != null) {
            volunteer.setCurrentScheduledHours(0);
            volunteer.setCurrentWorkCompleted(false);
            System.out.println("Scheduled hours and work status reset for volunteer " + volunteerID);
        } else {
            System.out.println("Volunteer not found. Unable to reset fields.");
        }
    }
    
    

    private static void extendVolunteerHours() {
        System.out.print("Enter Volunteer ID: ");
        String volunteerID = scanner.nextLine();
        System.out.print("Enter additional hours: ");
        double hours = scanner.nextDouble();
        scanner.nextLine();
        String result = VolunteerServiceStation.extendVolunteerHours(volunteerID, hours);
        System.out.println(result);
    }

    private static void toggleActiveStatus() {
        System.out.print("Enter Volunteer ID to toggle status: ");
        String volunteerID = scanner.nextLine();
        String result = VolunteerServiceStation.toggleActiveStatus(volunteerID);
        System.out.println(result);
    }

    private static void viewAllVolunteers() {
        System.out.println("=== All Registered Volunteers ===");
        if (volunteers.isEmpty()) {
            System.out.println("No volunteers registered.");
        } else {
            for (Volunteer volunteer : volunteers) {
                System.out.printf(
                        "Volunteer ID: %s, Name: %s, Current Scheduled Hours: %.2f, Work Completed: %b, Total Hours Worked: %.2f, Active: %b, Task Description: %s\n",
                        volunteer.getVolunteerID(),
                        volunteer.getName(),
                        volunteer.getCurrentScheduledHours(),
                        volunteer.isCurrentWorkCompleted(),
                        volunteer.getTotalHoursWorked(),
                        volunteer.isActive(),
                        volunteer.getCurrentDateTaskDescription());
            }
        }
    }

    private static void reloadVolunteers() {
        volunteers = Volunteer.getAllVolunteers();
    }
}
