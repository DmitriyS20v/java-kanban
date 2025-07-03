package ru.yandex.javacourse.finaltask4.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class Epic extends Task {

    private final List<Integer> subTasksId = new ArrayList();

    public Epic(String epicName, String epicDescription, int epicId) {

        super(epicName, epicDescription, epicId);
    }

    public Epic(Task task) {

        super(task.getTaskName(), task.getTaskDescription(), task.getStatus(), task.getTaskId());
    }


    public static TaskStatus checkEpicStatus(Epic epic, HashMap<Integer, SubTask> subTasks) {
        int numberOfSubTasksEpic = 0;
        int subTaskNew = 0;
        int subTaskFinished = 0;
        TaskStatus epicStatus;
        List<Integer> epicSubTasks = epic.getSubTasksId();
        if (epicSubTasks == null) {
            epicStatus = TaskStatus.NEW;
            return epicStatus;
        } else {
            for (int subTaskId : epicSubTasks) {
                numberOfSubTasksEpic++;
                SubTask subTask = subTasks.get(subTaskId);
                TaskStatus subTaskStatus = subTask.getStatus();
                if (subTaskStatus == TaskStatus.NEW) {
                    subTaskNew++;
                } else if (subTaskStatus == TaskStatus.DONE) {
                    subTaskFinished++;
                }
            }
        }

        if (numberOfSubTasksEpic == subTaskFinished) {
            epicStatus = TaskStatus.DONE;
        } else if (numberOfSubTasksEpic == subTaskNew) {
            epicStatus = TaskStatus.NEW;
        } else {
            epicStatus = TaskStatus.IN_PROGRESS;
        }
        return epicStatus;
    }


    public List<Integer> getSubTasksId() {
        return subTasksId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epic epic = (Epic) o;
        return super.equals(epic) &&
                Objects.equals(subTasksId, epic.subTasksId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTasksId);
    }
}