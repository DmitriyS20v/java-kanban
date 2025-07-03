package ru.yandex.javacourse.finaltask4.tasks;

public class SubTask extends Task {


    public SubTask(String nameSubTask, String descriptionSubTask, int subTaskId) {
        super(nameSubTask, descriptionSubTask, subTaskId);

    }


    public SubTask(Task task) {
        super(task.getTaskName(), task.getTaskDescription(), task.getStatus(), task.getTaskId());
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
