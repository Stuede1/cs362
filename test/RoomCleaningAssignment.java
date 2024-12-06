import java.io.*;
import java.util.*;

public class RoomCleaningAssignment {

    private static List<Staff> loadStaff() {
        List<Staff> staffList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(".\\files\\staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();  
                if (!line.isEmpty()) {  
                    String[] staffData = line.split(",\\s*");  
                    if (staffData.length == 3) {
                        String assignedRoom = staffData[2].trim();  
                        if ("Available".equalsIgnoreCase(assignedRoom)) {
                            assignedRoom = "";  
                        }
                        staffList.add(new Staff(Integer.parseInt(staffData[0]), staffData[1], assignedRoom));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading staff.txt: " + e.getMessage());
        }
        return staffList;
    }
    

    // Method to load rooms from the room.txt file
    private static List<Room> loadRooms() {
        List<Room> roomList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(".\\files\\room.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] roomData = line.split(",\\s*");  
                    if (roomData.length == 3) {
                        roomList.add(new Room(roomData[0], roomData[1], roomData[2]));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading room.txt: " + e.getMessage());
        }
        return roomList;
    }

    
    private static void displayStaffAvailability(List<Staff> staffList) {
        for (Staff staff : staffList) {
            System.out.println("Staff ID: " + staff.getId() + ", Name: " + staff.getName() + ", Assigned Room: " + (staff.getAssignedRoom().isEmpty() ? "Available" : staff.getAssignedRoom()));
        }
    }

    
    private static void displayRoomAvailability(List<Room> roomList) {
        for (Room room : roomList) {
            String availability = room.getStatus().equals("Available") ? "Available" : "Occupied";
            System.out.println("Room ID: " + room.getRoomId() + ", Status: " + availability + ", Type: " + room.getRoomType());
        }
    }

    private static boolean assignStaffToRoom(List<Staff> staffList, int staffId, String roomId) {
        for (Staff staff : staffList) {
            if (staff.getId() == staffId) {
                if ("Available".equals(staff.getAssignedRoom())) {  // Check if staff is not already assigned
                    staff.setAssignedRoom(roomId);
                    return true;
                } else {
                    System.out.println("Staff member " + staff.getName() + " is already assigned to a room.");
                    return false;
                }
            }
        }
        System.out.println("Staff with ID " + staffId + " not found.");
        return false;
    }

    // Method to assign tasks to a staff member
    private static void assignTaskToStaff(List<Staff> staffList, int staffId, String taskDescription, List<String> checklist) {
        for (Staff staff : staffList) {
            if (staff.getId() == staffId) {
                Task task = new Task(taskDescription, checklist);
                // Save task to a file for staff member
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\files\\tasks.txt", true))) {
                    writer.write("Staff ID: " + staff.getId() + " Name: " + staff.getName() + ", Task: " + task.getTaskDescription());
                    writer.newLine();
                    for (String step : task.getChecklist()) {
                        writer.write("Step: " + step);
                        writer.newLine();
                    }
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println("Error writing task: " + e.getMessage());
                }
                System.out.println("Task assigned to " + staff.getName());
                return;
            }
        }
        System.out.println("Staff with ID " + staffId + " not found.");
    }

    // Method to view tasks for a specific staff member
    private static void viewTasksForStaff(int staffId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(".\\files\\tasks.txt"))) {
            String line;
            boolean taskFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Staff ID: " + staffId)) {
                    taskFound = true;
                    System.out.println(line); // Print task line
                    while ((line = reader.readLine()) != null && line.startsWith("Step:")) {
                        System.out.println(line); // Print task steps
                    }
                    System.out.println(); // Add extra line after task
                }
            }
            if (!taskFound) {
                System.out.println("No tasks found for Staff ID " + staffId);
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Load staff and rooms data
        List<Staff> staffList = loadStaff();
        List<Room> roomList = loadRooms();

        // Main menu options
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Select an option:");
            System.out.println("1. Assign task to staff already assigned to a room.");
            System.out.println("2. Assign available staff to a room.");
            System.out.println("3. View tasks for a staff member.");
            System.out.println("4. Exit.");
            System.out.println("5. View current staff assignments.");
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    {
                        System.out.println("Staff Already Assigned to Rooms:");
                        displayStaffAvailability(staffList);
                        System.out.print("\nEnter staff ID to assign a task to: ");
                        int staffId = scanner.nextInt();
                        scanner.nextLine();  
                        System.out.print("Enter task description for staff: ");
                        String taskDescription = scanner.nextLine();
                        System.out.print("Enter number of steps for the checklist: ");
                        int numSteps = scanner.nextInt();
                        scanner.nextLine(); 
                        List<String> checklist = new ArrayList<>();
                        for (int i = 0; i < numSteps; i++) {
                            System.out.print("Enter step " + (i + 1) + ": ");
                            checklist.add(scanner.nextLine());
                        }       
                        assignTaskToStaff(staffList, staffId, taskDescription, checklist);
                        break;
                    }
                case 2:
                    {
                        System.out.println("\nAvailable Staff Members:");
                        displayStaffAvailability(staffList);
                        System.out.println("\nAvailable Rooms:");
                        displayRoomAvailability(roomList);
                        System.out.print("\nEnter staff ID to assign to a room: ");
                        int staffId = scanner.nextInt();
                        scanner.nextLine();  
                        System.out.print("Enter room ID to assign staff to: ");
                        String roomId = scanner.nextLine();
                        if (assignStaffToRoom(staffList, staffId, roomId)) {
                            System.out.println("Staff assigned to room successfully.");
                        }       
                        break;
                    }
                case 3:
                    {
                        System.out.print("Enter staff ID to view tasks: ");
                        int staffId = scanner.nextInt();
                        scanner.nextLine();  
                        viewTasksForStaff(staffId);
                        break;
                    }
                case 4:
                    System.out.println("Exiting program.");
                    break;
                case 5: 
                System.out.println("\nCurrent Staff Assignments:");
                displayStaffAvailability(staffList);
                break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        // Save staff assignments
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\files\\staff.txt"))) {
            for (Staff staff : staffList) {
                writer.write(staff.getId() + ", " + staff.getName() + ", " + (staff.getAssignedRoom().isEmpty() ? "Available" : staff.getAssignedRoom()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving staff assignments: " + e.getMessage());
        }
    }
}
