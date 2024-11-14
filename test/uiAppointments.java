import java.util.List;
import java.util.Scanner;

public class uiAppointments {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        OUTER:
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View all appointments");
            System.out.println("2. Schedule a new appointment");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1/2/3): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    // View all appointments
                    System.out.println("\n=== All Appointments ===");
                    List<Appointment> allAppointments = Appointment.getAllAppointments();
                    if (allAppointments.isEmpty()) {
                        System.out.println("No appointments scheduled.");
                    } else {
                        for (Appointment app : allAppointments) {
                            System.out.println(app);
                        }
                    }
                }
                case "2" -> {
                    // Schedule a new appointment
                    System.out.println("Enter Appointment ID: ");
                    String appointmentID = scanner.nextLine();
                    System.out.println("Enter Patient ID: ");
                    String patientID = scanner.nextLine();
                    System.out.println("Enter Patient Name: ");
                    String patientName = scanner.nextLine();
                    System.out.println("Enter Patient Date of Birth (yyyy-MM-dd): ");
                    String patientDOB = scanner.nextLine();
                    Patient patient = new Patient(patientID, patientName, patientDOB);
                    System.out.println("Enter Doctor ID: ");
                    String doctorID = scanner.nextLine();
                    System.out.println("Enter Doctor Name: ");
                    String doctorName = scanner.nextLine();
                    Doctor doctor = new Doctor(doctorID, doctorName);
                    System.out.println("Enter Appointment Date (yyyy-MM-dd): ");
                    String date = scanner.nextLine();
                    System.out.println("Enter Appointment Time (HH:mm): ");
                    String time = scanner.nextLine();
                    Appointment appointment = new Appointment(appointmentID, date, time, patient, doctor);
                    System.out.println(appointment.scheduleAppointment());
                    // Option to view appointments for a specific doctor
                    System.out.println("\nDo you want to view appointments for a specific doctor? (yes/no)");
                    String viewDoctorAppointments = scanner.nextLine();
                    if (viewDoctorAppointments.equalsIgnoreCase("yes")) {
                        System.out.println("Enter Doctor ID: ");
                        String doctorIDToSearch = scanner.nextLine();
                        List<Appointment> doctorAppointments = Appointment.getAllAppointmentsForDoctor(doctorIDToSearch);
                        if (doctorAppointments.isEmpty()) {
                            System.out.println("No appointments found for Doctor ID: " + doctorIDToSearch);
                        } else {
                            System.out.println("\n=== Appointments for Doctor " + doctorIDToSearch + " ===");
                            for (Appointment app : doctorAppointments) {
                                System.out.println(app);
                            }
                        }
                    }
                }
                case "3" -> {
                    // Exit the program
                    System.out.println("Exiting the system. Goodbye!");
                    break OUTER;
                }
                default -> // Invalid choice
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }
}