import java.util.Objects;

public class Task {
    private TaskStatus status = TaskStatus.NEW;
    private final String taskName;
    private String taskDescription;


    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public Task(String taskName, String taskDescription, TaskStatus status) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return "Название: " + taskName + "\nОписание: " + taskDescription + "\nСтатус: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return
                status == task.status &&
                        Objects.equals(taskName, task.taskName) &&
                        Objects.equals(taskDescription, task.taskDescription);
    }

    @Override
    public int hashCode() {
        int hash = 21;
        if (taskName != null) {
            hash = hash + taskName.hashCode();
        }
        hash = hash * 31;
        if (taskDescription != null) {
            hash = hash + taskDescription.hashCode();
        }
        return hash;
    }


}
