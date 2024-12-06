import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
* @author Yunpeng Lyu
*
* Code for Visitor
*
*/

public class Visitor {
    private static int visitorCounter = 0;
    private String visitorID;
    private String name;
    private String visitingPatientID;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private static final String VISITOR_FILE = "files/visitors.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Visitor(String name, String visitingPatientID) {
        this.name = name;
        this.visitingPatientID = visitingPatientID;
        this.visitorID = generateVisitorID();
    }

    private static String generateVisitorID() {
        return "V" + System.currentTimeMillis();
        //return "V" + (++visitorCounter);
    }



    public String getVisitorID() {
        return visitorID;
    }
    public String getName() {
        return name;
    }

    public String getVisitingPatientID() {
        return visitingPatientID;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public static Visitor findVisitorByID(String visitorID) {
        List<Visitor> visitors = getAllVisitors();
        for (Visitor visitor : visitors) {
            if (visitor.getVisitorID().equals(visitorID)) {
                return visitor;
            }
        }
        return null;  
    }



    public String registerVisitor() {
        if (Patient.findPatientByID(this.visitingPatientID) == null) {
            return "Registration failed: No such patient with ID " + this.visitingPatientID;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VISITOR_FILE, true))) {

            writer.write(this.visitorID + "," + this.name + "," + this.visitingPatientID + ",,");
            writer.newLine();
            return "Visitor registered successfully. Visitor ID: " + this.visitorID;
        } catch (IOException e) {
            return "Failed to register visitor: " + e.getMessage();
        }
    }
    
    public void checkInVisitor() {
        this.checkInTime = LocalDateTime.now(); // Set the current time as check-in time
        this.checkOutTime = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59); 
        

        try {
            List<Visitor> updatedVisitors = getAllVisitors();
            for (Visitor visitor : updatedVisitors) {
                if (visitor.getVisitorID().equals(this.visitorID)) {
                    visitor.setCheckInTime(this.checkInTime);
                    visitor.setCheckOutTime(this.checkOutTime);
                    break; 
                }
            }
            updateVisitorFile(updatedVisitors); 
        } catch (IOException e) {
            System.err.println("Error updating visitor file during check-in: " + e.getMessage());
        }
    }
    
    public void checkOutVisitor() {
        this.checkOutTime = LocalDateTime.now(); 
    


        try {
            List<Visitor> updatedVisitors = getAllVisitors(); 
            for (Visitor visitor : updatedVisitors) {
                if (visitor.getVisitorID().equals(this.visitorID)) {
                    visitor.setCheckOutTime(this.checkOutTime);
                    break; 


                }
            }
            updateVisitorFile(updatedVisitors); 
            
        } catch (IOException e) {
            System.err.println("Error updating visitor file during check-out: " + e.getMessage());
        }
    }

    	


    public void extendVisitTime() {
        if (this.checkOutTime != null) {
            this.checkOutTime = this.checkOutTime.plusDays(1);
            System.out.println("Debug: Extending visit time for visitor " + this.visitorID + " to " + this.checkOutTime.format(formatter));
    
            try {
                List<Visitor> updatedVisitors = getAllVisitors();
                boolean visitorFound = false;
                for (Visitor visitor : updatedVisitors) {
                    if (visitor.getVisitorID().equals(this.visitorID)) {
                        visitor.setCheckOutTime(this.checkOutTime);
                        visitorFound = true;
                        System.out.println("Debug: Updated visitor " + visitor.getVisitorID() + " in the list with new check-out time " + visitor.getCheckOutTime().format(formatter));
                        break;
                    }
                }
    
                if (!visitorFound) {
                    System.err.println("Debug: Visitor " + this.visitorID + " not found in visitor list for updating.");
                }
    
                System.out.println("Debug: Attempting to update visitor file with new extended times.");
                updateVisitorFile(updatedVisitors);
                System.out.println("Debug: Visitor file updated successfully.");
            } catch (IOException e) {
                System.err.println("Error updating visitor file during visit extension: " + e.getMessage());
            }
        } else {
            System.err.println("Error: No existing check-out time to extend.");
        }
    }
    
    

    public static List<Visitor> getAllVisitors() {
        List<Visitor> visitors = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(VISITOR_FILE));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length >= 3) { 
                    Visitor visitor = new Visitor(data[1], data[2]);
                    visitor.visitorID = data[0];
                    visitor.checkInTime = (data.length > 3 && !data[3].isEmpty()) ? LocalDateTime.parse(data[3], formatter) : null;
                    visitor.checkOutTime = (data.length > 4 && !data[4].isEmpty()) ? LocalDateTime.parse(data[4], formatter) : null;
                    visitors.add(visitor);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading visitor file: " + e.getMessage());
        }
        return visitors;
    }

    public static void updateVisitorFile(List<Visitor> visitors) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VISITOR_FILE, false))) { 
            for (Visitor visitor : visitors) {
                writer.write(String.format("%s,%s,%s,%s,%s",
                                           visitor.getVisitorID(),
                                           visitor.getName(),
                                           visitor.getVisitingPatientID(),
                                           visitor.getCheckInTime() != null ? formatter.format(visitor.getCheckInTime()) : "",
                                           visitor.getCheckOutTime() != null ? formatter.format(visitor.getCheckOutTime()) : ""));
                writer.newLine();
            }
        }
    }
    
}