import java.util.*;

public class Task {
    private String taskDescription;
    private List<String> checklist;

    public Task(String taskDescription, List<String> checklist) {
        this.taskDescription = taskDescription;
        this.checklist = checklist;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public List<String> getChecklist() {
        return checklist;
    }
}