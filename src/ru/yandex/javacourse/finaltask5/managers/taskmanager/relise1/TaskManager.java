package ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1;

import ru.yandex.javacourse.finaltask5.tasks.Epic;
import ru.yandex.javacourse.finaltask5.tasks.SubTask;
import ru.yandex.javacourse.finaltask5.tasks.Task;
import ru.yandex.javacourse.finaltask5.tasks.TaskStatus;

import java.util.List;

public interface TaskManager {
    // Получение списков
    List<SubTask> getSubTasks();

    List<Epic> getEpics();

    List<Task> getTasks();

    // Удаление всех задач и обнуление идентификатора
    void deleteAllTask();

    void deleteAllEpic();

    void deleteAllSubTask();

    //Получение задач по индетификатору
    Task getTask(int taskID);

    Epic getEpic(int epicID);

    SubTask getSubTask(int subTaskID);

    //Получение любой из подзадач указанием идентификатора
    Object gettingByIdentifier(int identifier);

    // Создание задач и эпиков
    void addTask(String taskName, String taskDescription);

    <T extends Task> void addTasks(T task);

    void addEpic(String taskName, String taskDescription);


    void addSubTask(String taskName, String taskDescription, int epicId);


    // Обновление задач
    <T extends Task> void updateTask(T newTask);

    List<SubTask> getEpicSubTask(int tasksID);

    // удаление по индетификатору
    void deleteTaskByIdentificator(int tasksID);

    // Выбор пользователем статуса задачи
    TaskStatus selectTaskStatus(int command);

    List<Task> getHistory();

}
