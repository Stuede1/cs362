import java.util.List;
import java.util.Scanner;

/**
* @author Yunpeng Lyu
*
* Code for VisitorManagement UI
*
*/
public class UIVisitorManagement {
    private static List<Visitor> visitors = Visitor.getAllVisitors();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Hospital Visitor Management System ===");
            System.out.println("1. Register Visitor");
            System.out.println("2. Check-in Visitor");
            System.out.println("3. Check-out Visitor");
            System.out.println("4. Extend Visit Time");
            System.out.println("5. View All Visitors");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    registerVisitor();
                    break;
                case 2:
                    checkInVisitor();
                    break;
                case 3:
                    checkOutVisitor();
                    break;
                case 4:
                    extendVisitTime();
                    break;
                case 5:
                    viewAllVisitors();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            reloadVisitors();  
        }
    }

    private static void registerVisitor() {
        System.out.print("Enter Visitor's Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient ID visiting: ");
        String patientID = scanner.nextLine();
    
        Visitor visitor = new Visitor(name, patientID);
        String result = visitor.registerVisitor();
        System.out.println(result);
    }
    
    private static void checkInVisitor() {
        System.out.print("Enter Visitor ID to check-in: ");
        String visitorID = scanner.nextLine();
        System.out.print("Enter the Patient ID they are visiting: ");
        String patientID = scanner.nextLine();

        String result = Reception.checkInVisitor(visitorID, patientID);
        System.out.println(result);
    }

    private static void checkOutVisitor() {
        System.out.print("Enter Visitor ID to check-out: ");
        String visitorID = scanner.nextLine();

        String result = Reception.checkOutVisitor(visitorID);
        System.out.println(result);
    }

    private static void extendVisitTime() {
        System.out.print("Enter Visitor ID to extend visit time: ");
        String visitorID = scanner.nextLine();

        String result = Reception.extendVisitorTime(visitorID);
        System.out.println(result);
    }

    private static void viewAllVisitors() {
        System.out.println("=== All Registered Visitors ===");
        if (visitors.isEmpty()) {
            System.out.println("No visitors registered.");
        } else {
            for (Visitor visitor : visitors) {
                System.out.println("Visitor ID: " + visitor.getVisitorID() + ", Name: " + visitor.getName() + ", Visiting Patient ID: " + visitor.getVisitingPatientID());
            }
        }
    }

    private static void reloadVisitors() {
        visitors = Visitor.getAllVisitors();  
    }
}
