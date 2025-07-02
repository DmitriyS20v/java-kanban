import java.util.HashMap;


public class Epic extends Task {

    public Epic(String epicName, String epicDescription) {
        super(epicName, epicDescription);
    }

    public Epic(Task task) {
        super(task.getTaskName(), task.getTaskDescription(), task.getStatus());
    }


    public static TaskStatus checkEpicStatus(Epic epic, HashMap<Integer, SubTask> subTasks) {
        int hashCode = epic.hashCode();
        int numberOfSubTasksEpic = 0;
        int subTaskNew = 0;
        int subTaskFinished = 0;
        TaskStatus epicStatus = epic.getStatus();
        for (SubTask sub : subTasks.values()) {
            if (sub.getHashCodeEpic() == hashCode) {
                numberOfSubTasksEpic++;
                if (sub.getStatus() == TaskStatus.NEW) {
                    subTaskNew++;
                } else if (sub.getStatus() == TaskStatus.DONE) {
                    subTaskFinished++;
                }

            }
        }
        if (numberOfSubTasksEpic == 0) {
            System.out.println("У эпика нет подзадач.");
            return epicStatus;
        } else if (numberOfSubTasksEpic == subTaskFinished) {
            epicStatus = TaskStatus.DONE;
        } else if (numberOfSubTasksEpic == subTaskNew) {
            epicStatus = TaskStatus.NEW;
        } else {
            epicStatus = TaskStatus.IN_PROGRESS;
        }
        return epicStatus;
    }
}