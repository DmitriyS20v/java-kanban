package ru.yandex.javacourse.finaltask4.tasks;


import java.util.Objects;

public class Task {
    private TaskStatus status = TaskStatus.NEW;
    private final String taskName;
    private final String taskDescription;
    private final int taskId;

    public Task(String taskName, String taskDescription, int taskId) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskId = taskId;
    }

    public Task(String taskName, String taskDescription, TaskStatus status, int taskId) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.taskId = taskId;
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

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "Идентификатор № " + taskId + "\nНазвание: " + taskName + "\nОписание: " + taskDescription + "\nСтатус: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return
                status == task.status &&
                        Objects.equals(taskName, task.taskName) &&
                        Objects.equals(taskDescription, task.taskDescription) &&
                        taskId == task.taskId;

    }

    @Override
    public int hashCode() {
        int hash = 21;
        if (taskName != null) {
            hash = hash + taskName.hashCode();
        }
        hash = hash * 31 + taskDescription.hashCode();
        if (taskDescription != null) {
            hash = hash + taskDescription.hashCode();
        }
        return hash;
    }


}
