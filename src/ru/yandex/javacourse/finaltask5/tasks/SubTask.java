package ru.yandex.javacourse.finaltask5.tasks;

public class SubTask extends Task {

    private final int epicId;

    public SubTask(String nameSubTask, String descriptionSubTask, int subTaskId, int epicId) {
        super(nameSubTask, descriptionSubTask, subTaskId);
        this.epicId = epicId;
    }


    public SubTask(Task task, int epicId) {
        super(task.getTaskName(), task.getTaskDescription(), task.getStatus(), task.getTaskId());
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubTask sub = (SubTask) o;
        return super.equals(sub);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
