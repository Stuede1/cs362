import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
// import java.io.IOException;




class Appointment {
    private String details;
    private String date;
    private String time;
    private String patient;
    private String id;

    public Appointment(String details, String date, String time, String patient, String id) {
        this.details = details;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPatient() {
        return patient;
    }

    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Appointment: " + details + "\nDate: " + date + "\nTime: " + time + "\nPatient: " + patient + "\nID: " + id;
    }


    //Notes: Maybe add variable to distinguish month at the front for organization?
    //       Maybe have 12 different files for each month
    public String toFile() {
        return "ID: " + id + "    |    Date: " + date + "    |    Time: " + time + "    |    Patient: " + patient + "    |    Details: " + details;
    }

}




class User {
    private String userID;
    private String userName;
    private Integer doctorID;

    // private ArrayList<Appointment> userAppointments;
    

    public User(String userID, String userName, Integer doctorID) {
        this.userID = userID;
        this.userName = userName;
        this.doctorID = doctorID;

    }

    public String get_userID() {
        return userID;
    }

    public String get_userName() {
        return userName;
    }

    public Integer getDoctorID() {
        return doctorID;
    }


    // public ArrayList<Appointment> get_userAppointments() {
    //     return userAppointments;
    // }

    @Override
    public String toString() {
        return "User ID: " + userID + "    |    Name: " + userName + "    |    Doctor ID: " + doctorID;
        // return "User: " + userName + "\nID: " + userID + "\nAppointments: " + userAppointments.toString();
    }

}


class Doctor {
    private String userID;
    private String userName;
    private int available;
    private int specialty;


    public Doctor(String userID, String userName, int available, int specialty) {
        this.userID = userID;
        this.userName = userName;
        this.available = available;
        this.specialty = specialty;
    }


    public String get_userID() {
        return userID;
    }

    public String get_userName() {
        return userName;
    }

    public Integer getAvailability() {
        return available;
    }

    public Integer getSpecialty() {
        return specialty;
    }
    public void setAvailability (int availability) {
        this.available = availability;
    }

    public void setAvailability (int lineNumber, String data) {
        try {
            Path path = Paths.get("staffData.txt");
            List<String> lines = Files.readAllLines(path);
            lines.set(lineNumber, data);
            Files.write(path, lines);
        } catch (Exception e) {

        }

    }


    @Override
    public String toString() {
        return "User ID: " + userID + "    |    Name: " + userName + "    |    Available: " + available + "    |    Specialty: " + specialty;
    }


    public String specialDoctor() {
        return "Name: " + userName + "    |    Available: " + available + "    |    Specialty: " + specialty;

    }
}




class Calendar {

    private ArrayList<Appointment> appts;

    public Calendar() {
        appts = new ArrayList<>();
    }


    public void addAppointment(Appointment appt) {
        appts.add(appt);
    }


    public void viewAppointments() {
        if (appts.isEmpty()) {
            System.out.println("No appointments today.");
        } else {
            for (int i = 0; i < appts.size(); i++) {
                System.out.println("Appoitnment #" + (i + 1));
                System.out.println(appts.get(i));
                System.out.println("-----------------------------");
            }
        }
    }
}


public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<Calendar> calendar = new ArrayList<Calendar>();
        Scanner scnr = new Scanner(System.in);
        boolean exit = false;


        for(int i = 0; i < 12; i++) {
            Calendar temp = new Calendar();
            calendar.add(temp);
        }


        while (!exit) {
            System.out.println("Welcome to the Hospital Portal!  Please select an option below.\n");
            System.out.println("[0] Find Doctor");
            System.out.println("[1] Add Appointment");
            System.out.println("[2] View Appointments");
            System.out.println("[3] Add User");
            System.out.println("[4] View Users");
            System.out.println("[5] View Calendar");
            System.out.println("[6] Exit\n");
            System.out.print("Choose an option: ");
            int option = scnr.nextInt();
            int doctorOption = 9;

            scnr.nextLine();  

            switch (option) {

                case 0:
                    try {
                        System.out.println();
                        System.out.println("-----------------------------------------------------------------\n");

                        int userOption = 10;

                        System.out.println("Select Issue/Symptom: ");
                        System.out.println("[1] Head");
                        System.out.println("[2] Back");
                        System.out.println("[3] Joint");
                        System.out.println("[4] Leg");
                        System.out.println("[5] Stomach");
                        System.out.println("[6] Emotional");
                        System.out.println("[7] Upper Body");
                        System.out.println("[8] Lower Body");
                        System.out.println("[9] Other");
                        System.out.println("[0] Exit\n");
                        System.out.print("Choose an option: ");

                        userOption = scnr.nextInt();

                        if (userOption > 9) {
                            System.out.println("Invalid Option: ");
                            break;
                        }

                        System.out.print("Current Doctors: ");

                        BufferedReader spDoc = new BufferedReader(new FileReader("staffData.txt"));
                        String docLine = spDoc.readLine();

                        String target = "Specialty: " + Integer.toString(userOption);

                        Doctor spD = null;


                        if (userOption > 9){
                            System.out.println("No Doctor exists with specified ID");
                        } else {
                            while (docLine != null) {
                                if(docLine.contains(target)){
                                    List<String> doctor1 = Arrays.asList(docLine.split(" "));
                                    String dID = doctor1.get(2);

                                    // List<String> doctor2 = Arrays.asList(dline.split("Name: "));
                                    List<String> doctor2 = Arrays.asList(docLine.split(" "));
                                    String doctorName = doctor2.get(11);

                                    List<String> doctor3 = Arrays.asList(docLine.split(" "));
                                    int a = Integer.valueOf(doctor3.get(20));

                                    int dtemp = 0;

                                    spD = new Doctor(dID, doctorName, a, dtemp);
                                    docLine = null;
                                    spDoc.close();
                                    break;
                                }
                                docLine = spDoc.readLine();
                            }
                            spDoc.close();
                        }

                        spDoc.close();


                        String temp1 = spD.get_userID();
                        String temp2 = spD.get_userName();
                        Integer temp3 = spD.getAvailability();
                        Integer temp4 = Integer.parseInt(temp1);




                        spD = new Doctor(temp1, temp2, temp3, userOption);


                        String doctorDetails = spD.specialDoctor();


                        System.out.println(doctorDetails);

                        doctorOption = userOption;

                        System.out.println("");

                        break;

    
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        break;
                    }


                case 1:
                    try {
                        String calendarFile = "calendarFile.txt";
                        FileWriter fw = new FileWriter(calendarFile, true);

                        System.out.println();
                        System.out.println("-----------------------------------------------------------------\n");

                        System.out.print("Enter Appointment Details: ");
                        String details = scnr.nextLine();
                        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                        String date = scnr.nextLine();
    
                        boolean isValid = true;
    
                        List<String> items = Arrays.asList(date.split("-"));

                        //Assume entered correctly, bc throws error otherwise
                        int year = Integer.valueOf(items.get(0));
                        int month = Integer.valueOf(items.get(1));
                        int day = Integer.valueOf(items.get(2));
    
                        if((year < 2024) || (month < 1) || (month > 12) || (day > 31) || (day < 1)) {
                            isValid = false;
                        }
    
                        if ((month == 2) && (day > 29)) {
                            isValid = false;
                        } 
    
                        if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
                            if (day > 30) {
                                isValid = false;
                            }
                        }
    
                        while (isValid == false) {
                            System.out.println("Invalid Date!  Please try again.\n");
                            System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                            date = scnr.nextLine();
    
                            items = Arrays.asList(date.split("-"));
    
                            year = Integer.valueOf(items.get(0));
                            month = Integer.valueOf(items.get(1));
                            day = Integer.valueOf(items.get(2));
    
                            if((year < 2024) || (month < 1) || (month > 12) || (day > 31) || (day < 1)) {
                                isValid = false;
                            } else if ((month == 2) && (day > 29)) {
                                isValid = false;
                            } else if( (month == 4) || (month == 6) || (month == 9) || (month == 11)) {
                                if (day > 30) {
                                    isValid = false;
                                } else {
                                    isValid = true;
                                }
                            } else {
                                isValid = true;
                            }
                        }
    
                        System.out.print("Enter Appointment Time (HH:MM): ");
                        String time = scnr.nextLine();

                        List<String> temp11 = Arrays.asList(time.split(":"));
                        // String s_hour = temp11.get(0).substring(0, 2);
                        String s_hour = temp11.get(0);

                        Integer hour = Integer.parseInt(s_hour);


                        //Integrate after changing to float
                        // List<String> temp22 = Arrays.asList(time.split(":"));
                        // String s_min = temp22.get(0).substring(0,2);
                        // Integer min = Integer.parseInt(s_min);



                        System.out.print("Enter Patient Name: ");
                        String patient = scnr.nextLine();
                        System.out.print("Enter Patient ID: ");
                        String patientID = scnr.nextLine();


                        BufferedReader reader2 = new BufferedReader(new FileReader("userData.txt"));
                        String line = reader2.readLine();
                
                        Doctor d = null;
                        String target = "User ID: " + patientID;
                        Integer doctorID = null;

                        while (line != null) {
                            if(line.contains(target)){
                                // System.out.println(line);
                                isValid = true;
                                List<String> userData = Arrays.asList(line.split("Doctor ID: "));
                                doctorID = Integer.valueOf(userData.get(1));
                                line = null;
                                reader2.close();
                                break;
                            }
                            line = reader2.readLine();
                        }

                        if (isValid == false) {
                            System.out.println("No Patient exists with specified ID");
                        }
                      
                        reader2.close();


                        BufferedReader dreader = new BufferedReader(new FileReader("staffData.txt"));
                        String dline = dreader.readLine();

                        String dtarget = "User ID: " + doctorID;

                        if (doctorID == null){
                            System.out.println("No Doctor exists with specified ID");
                        } else {
                            while (dline != null) {
                                if(dline.contains(dtarget)){
                                    List<String> doctor1 = Arrays.asList(dline.split(" "));
                                    String dID = doctor1.get(2);

                                    // List<String> doctor2 = Arrays.asList(dline.split("Name: "));
                                    List<String> doctor2 = Arrays.asList(dline.split(" "));
                                    String doctorName = doctor2.get(11);

                                    List<String> doctor3 = Arrays.asList(dline.split("Available: "));
                                    int a = Integer.valueOf(doctor3.get(1));

                                    int dtemp = 0;

                                    d = new Doctor(dID, doctorName, a, dtemp);
                                    dline = null;
                                    dreader.close();
                                    break;
                                }
                                dline = dreader.readLine();
                            }
                            dreader.close();
                        }

                        dreader.close();

                        Integer d_avail = d.getAvailability();
                        // System.out.println("POOP" + d_avail);
                        System.out.println();
                        if (d_avail <= 0 || ((d.getAvailability()-hour) < 0)) {
                            System.out.println("No Availability for Doctor " + d.get_userName() + ".  Returning to Main Menu\n");
                            System.out.println("-----------------------------------------------------------------\n");
                            break;
                        } else {

                            String temp1 = d.get_userID();
                            String temp2 = d.get_userName();
                            Integer temp3 = d.getAvailability();
                            Integer temp4 = Integer.parseInt(temp1);

                            Integer temp5 = 0;

                            temp3 = temp3 - hour;



                            d = new Doctor(temp1, temp2, temp3, temp5);


                            String doctorDetails = d.toString();

                            d.setAvailability (temp4-1, doctorDetails);


                            System.out.println(doctorDetails);

                        }

                        BufferedReader reader = new BufferedReader(new FileReader("calendarFile.txt"));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();
                        lines++;
                        String id = Integer.toString(lines);


                        Appointment appt = new Appointment(details, date, time, patient, id);
                        calendar.get(month-1).addAppointment(appt);
                        System.out.println();
                        System.out.println("Appointment added successfully!");

                        String newAppt = appt.toFile();


                        String path = id + ".txt";
                        File newFile = new File(path);
                        Boolean fileExists = false;


                        if (newFile.createNewFile()) {
                            fileExists = true;
                            System.out.println("Appointment file created: Appointment" + newFile.getName());
                            System.out.println();
                            System.out.println("-----------------------------------------------------------------\n");
                        } else {
                            System.out.println("Appointment file already exists.");
                            System.out.println();
                            break;
                        }

                        if (fileExists) {
                            FileWriter fileData = new FileWriter(newFile, true);
                            fileData.write(newAppt + "\n");
                            fileData.close();
                        }

                        fw.write(newAppt + "\n");
                        fw.close();


                        break;

                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        break;
                    }

                case 2:
                    try {
                        System.out.println();
                        System.out.println("-----------------------------------------------------------------\n");
                        System.out.print("To view all appointments, press [a].\n");
                        System.out.print("Otherwise, to select an individual appointment, input the appointment ID:");

                        String userInput = scnr.nextLine();

                        if (userInput.equals("a")) {
                            BufferedReader reader1 = new BufferedReader(new FileReader("calendarFile.txt"));
                            String line = reader1.readLine();
                
                            System.out.println();

                            while (line != null) {
                                System.out.println(line);
                                line = reader1.readLine();
                            }
                
                            reader1.close();
    
                            System.out.println("");
                            System.out.println("End of Appointment List.  Returning back to Main Menu.\n");
                            System.out.println("-----------------------------------------------------------------\n");

                            break;
                        }

                        boolean isValid = false;
                        int value = Integer.valueOf(userInput);


                        if (value > 0) {
                            BufferedReader reader2 = new BufferedReader(new FileReader("calendarFile.txt"));
                            String line = reader2.readLine();
                    
                            String target = "ID: " + userInput;
    
                            while (line != null) {
                                if(line.contains(target)){
                                    System.out.println(line);
                                    isValid = true;
                                    line = null;
                                    reader2.close();
                                    break;
                                }
                                line = reader2.readLine();
                            }
    
                            if (isValid == false) {
                                System.out.println("No Appointment exists with specified ID");
                            }
                          
                            reader2.close();

                            break;
                        }
                        
                    } catch (Exception e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                            break;
                    }
    
                    break;


                case 3:
                    try {
                        System.out.println("Select [d] for a Doctor User, or [p] for a Patient User. \n");
                        String userInput = scnr.nextLine();

                        if (userInput.equals("d")) {
                            String staffDataFile = "staffData.txt";
                            FileWriter fw = new FileWriter(staffDataFile, true);
    
                            System.out.println();
                            System.out.println("-----------------------------------------------------------------\n");
    
                            System.out.print("Enter Staff Name: ");
                            String userName = scnr.nextLine();
    
                            BufferedReader reader1 = new BufferedReader(new FileReader("staffData.txt"));
                            int lines = 0;
                            while (reader1.readLine() != null) lines++;
                            reader1.close();
                            lines++;
                            String doctorID = Integer.toString(lines);
    
                            System.out.print("Enter hours available per week: ");
                            String availableString = scnr.nextLine();
    
                            int availability = Integer.parseInt(availableString);

                            System.out.print("Enter Doctor's field: \n");
                            System.out.println("[1] Head");
                            System.out.println("[2] Back");
                            System.out.println("[3] Joint");
                            System.out.println("[4] Leg");
                            System.out.println("[5] Stomach");
                            System.out.println("[6] Emotional");
                            System.out.println("[7] Upper Body");
                            System.out.println("[8] Lower Body");
                            System.out.println("[9] Other");


                            int specialty = 10;


                            specialty = scnr.nextInt();

                            if (specialty > 9) {
                                specialty = 9;
                            } 

                            // System.out.println("[1] Head");
                            // System.out.println("[2] Back");
                            // System.out.println("[3] Joint");
                            // System.out.println("[4] Leg");
                            // System.out.println("[5] Stomach");
                            // System.out.println("[6] Emotional");
                            // System.out.println("[7] Upper Body");
                            // System.out.println("[8] Lower Body");
                            

    
                            
                            Doctor doctor = new Doctor(doctorID, userName, availability, specialty);
    
                            // String doctorString = doctor.toString() + "*";
                            String doctorString = doctor.toString();
    
    
    
                            fw.write(doctorString + "\n");
                            fw.close();
                
                            System.out.println();
                            System.out.println("Doctor added successfully!  Returning back to Main Menu.\n");
                            System.out.println("-----------------------------------------------------------------\n");
                            break;

                        } else if (userInput.equals("p")) {
                            String userDataFile = "userData.txt";
                            FileWriter fw = new FileWriter(userDataFile, true);

                            System.out.println();
                            System.out.println("-----------------------------------------------------------------\n");

                            System.out.print("Enter User Name: ");
                            String userName = scnr.nextLine();

                            BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
                            int lines = 0;
                            while (reader.readLine() != null) lines++;
                            reader.close();
                            lines++;
                            String userID = Integer.toString(lines);

                            System.out.print("Enter Doctor ID: ");
                            String doctorString = scnr.nextLine();
                            Integer doctorID = Integer.parseInt(doctorString);

                            User user = new User(userID, userName, doctorID);
                            String userString = user.toString();

                            fw.write(userString + "\n");
                            fw.close();
                
                            System.out.println();
                            System.out.println("User added successfully!  Returning back to Main Menu.\n");
                            System.out.println("-----------------------------------------------------------------\n");
                            break;
                        } else {
                            System.out.println("Invalid Option.  Returning to Menu\n");
                            System.out.println("-----------------------------------------------------------------\n");
                            break;
                        }

                        
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        break;
                    }


                case 4:
                    try {
                        System.out.println();
                        System.out.println("-----------------------------------------------------------------\n");

                        BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
                        String line = reader.readLine();
                        
            
                        while (line != null) {
                            System.out.println(line);
                            line = reader.readLine();
                        }
            
                        reader.close();


                        System.out.println("");
                        System.out.println("End of User List.  Returning back to Main Menu.\n");
                        System.out.println("-----------------------------------------------------------------\n");

                        break;
                        
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        break;
                    }

                case 5:
                    System.out.println();
                    System.out.println("-----------------------------------------------------------------\n");
                    // System.out.println("Viewing Calendar:");
                    System.out.print("Enter Desired Month (1-12): ");
                    String inputMonth = scnr.nextLine();

                    boolean isValid = true;

                    int month = Integer.valueOf(inputMonth);

                    String targetMonth;

                    if ((month < 1) || (month > 12)) {
                        isValid = false;
                    }
                    
                    while (isValid == false) {
                        System.out.println("Invalid Month.  Please select a month 1-12.\n");
                        inputMonth = scnr.nextLine();
                        month = Integer.valueOf(inputMonth);

                        if ((month < 1) || (month > 12)) {
                            isValid = false;
                        } else {
                            isValid = true;
                        }

                    }

                    if (month < 10){
                        targetMonth = "0" + inputMonth;
                    } else {
                        targetMonth = inputMonth;
                    }

                    BufferedReader reader = new BufferedReader(new FileReader("calendarFile.txt"));
                    String line = reader.readLine();

                    String target = "Date: 2024-" + targetMonth;

                    System.out.println();
    
                    while (line != null) {
                        if(line.contains(target)){
                            System.out.println(line);
                        }
                        line = reader.readLine();
                    }
        
                    reader.close();


                    System.out.println("");
                    System.out.println("End of Appointment List.  Returning back to Main Menu.\n");
                    System.out.println("-----------------------------------------------------------------\n");

                    break;

                case 6:
                    System.out.println();
                    System.out.println("Are you sure you want to exit?\n");
                    System.out.println("Press [y] to confirm, or [n] to go back.\n");

                    String userInput = scnr.nextLine();

                    if (userInput.equals("y")) {
                        exit = true;
                        break;
                    } else {
                        System.out.println("\nReturning back to Main Menu.\n");
                        System.out.println("-----------------------------------------------------------------\n");
                        break;
                    }


                case 7:
                    try {
                        System.out.print("Enter Staff ID: ");
                        String staffID = scnr.nextLine();
                        Integer lineNum = Integer.parseInt(staffID);

                        System.out.print("Enter New Availability: ");
                        String temp = scnr.nextLine();

                        Integer a1 = Integer.parseInt(temp);


                        BufferedReader sreader = new BufferedReader(new FileReader("staffData.txt"));
                        String sline = sreader.readLine();
                        Integer lineNumber = Integer.parseInt(staffID);

                        for (int i = 1; i != lineNumber; i++) {
                            sline = sreader.readLine();
                        }
                            
                        List<String> val1 = Arrays.asList(sline.split(" "));
                        String var1 = val1.get(2);

                        List<String> val2 = Arrays.asList(sline.split(" "));
                        String var2 = val2.get(11);


                        sline = null;
                        sreader.close();

                        int var4 = 0;

                        Doctor d = new Doctor(var1, var2, a1, var4);

                        String doctorString = d.toString();

                        d.setAvailability (lineNum-1, doctorString);
                        break;

                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        break;
                    }


                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        scnr.close();
        System.out.println("\nExiting Portal.  Goodbye!\n");
        System.out.println("-----------------------------------------------------------------\n");
    }
}


