import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
 * Author: Joe Faith
 * 
 * Class Description:
 * the ResearchAssigment class takes a doctor and checks what types of research projects
 * are available to be worked on depending on the doctors specialty
 */
public class ResearchAssignment {

    // Class to represent a research project
    static class ResearchProject implements ResearchInterface {
        String projectName;
        String strengthRequired;
        List<String> itemsRequired;

        public ResearchProject(String projectName, String strengthRequired, List<String> itemsRequired) {
            this.projectName = projectName;
            this.strengthRequired = strengthRequired;
            this.itemsRequired = itemsRequired;
        }

        @Override
        public String getProjectName() {
            return this.projectName;
        }
        public String getStrengthRequired() {
            return this.strengthRequired;
        }

        @Override
        public List<String> getItemsRequired() {
            return this.itemsRequired;
        }

        @Override
        public String toString() {
            return "ResearchProject{projectName='" + projectName + "', strengthRequired='" + strengthRequired + "', itemsRequired=" + itemsRequired + "}";
        }

        @Override
        public String getSpecialtyRequired() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getSpecialtyRequired'");
        }
    }

    // Method to read research projects from a file
    public List<ResearchProject> readResearchProjects(String fileName) throws IOException {
        List<ResearchProject> researchProjects = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
            String projectName = values[0];
            String strengthRequired = values[1];
            List<String> itemsRequired = List.of(values[2].split(","));
            researchProjects.add(new ResearchProject(projectName, strengthRequired, itemsRequired));
        }
        br.close();
        return researchProjects;
    }

    // Method to read doctor data from a file
    public List<Doctor> readDoctors(String fileName) throws IOException {
        List<Doctor> doctors = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String name = values[0];
            String specialty = values[1];
            doctors.add(new Doctor(name, specialty));
        }
        br.close();
        return doctors;
    }

    // Method to assign research projects to doctors
    public void assignProjectsToDoctors(List<Doctor> doctors, List<ResearchProject> projects) {
        for (Doctor doctor : doctors) {
            System.out.println("Doctor: " + doctor.name + ", Specialty: " + doctor.specialty); // Print doctor details
            for (ResearchProject project : projects) {
                if (doctor.specialty.equalsIgnoreCase(project.getStrengthRequired())) {
                    System.out.println("Assigned to project: " + project.getProjectName());
                    System.out.println("Items required for the research: " + project.getItemsRequired());
                    break; // Assuming one project per doctor
                }
            }
        }
    }

    // Method to run the whole assignment process
    public void runAssignment(String doctorsFile, String projectsFile) {
        try {
            // Read doctor data from file
            List<Doctor> doctors = readDoctors(doctorsFile);

            // Read research projects from file
            List<ResearchProject> projects = readResearchProjects(projectsFile);

            // Assign projects to doctors based on strengths
            assignProjectsToDoctors(doctors, projects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
