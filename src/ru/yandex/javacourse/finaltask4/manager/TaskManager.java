package ru.yandex.javacourse.finaltask4.manager;

import ru.yandex.javacourse.finaltask4.tasks.Epic;
import ru.yandex.javacourse.finaltask4.tasks.SubTask;
import ru.yandex.javacourse.finaltask4.tasks.Task;
import ru.yandex.javacourse.finaltask4.tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class TaskManager {
    private int taskID = 0;
    private TaskStatus status;


    public final HashMap<Integer, Task> tasks = new HashMap<>();
    public final HashMap<Integer, Epic> epics = new HashMap<>();
    public final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private final int keyEpics = 0; //Используется для тестирования при добавлении подзадачи


    // Получение списков
    public List<SubTask> getSubTasks() {
        List<SubTask> subTask = new ArrayList<>();
        for (SubTask sub : subTasks.values()) {
            subTask.add(sub);
        }
        return subTask;
    }

    public List<Epic> getEpics() {
        List<Epic> epic = new ArrayList<>();
        for (Epic e : epics.values()) {
            epic.add(e);
        }
        return epic;
    }

    public List<Task> getTasks() {
        List<Task> task = new ArrayList<>();
        for (Task t : tasks.values()) {
            task.add(t);
        }
        return task;
    }

    // Удаление всех задач и обнуление идентификатора
    public void deleteAllTask() {
        tasks.clear();
    }

    public void deleteAllEpic() {
        epics.clear();
        subTasks.clear(); // Если мы удаляем все эпики значит удаляем и подзадачи
    }

    public void deleteAllSubTask() {
        for (Epic epic : epics.values()) {
            List<Integer> subTasksID = new ArrayList<>(epic.getSubTasksId());
            for (int subID : subTasksID) {
                subTasks.remove(subID);
                epic.getSubTasksId().remove(Integer.valueOf(subID));
            }
            Epic.checkEpicStatus(epic, subTasks);
        }
    }

    //Получение задач по индетификатору
    public Task getTask(int taskID) {
        return tasks.get(taskID);
    }

    public Epic getEpic(int epicID) {
        return epics.get(epicID);
    }

    public SubTask getSubTask(int subTaskID) {
        return subTasks.get(subTaskID);
    }

    //Получение любой из подзадач указанием идентификатора
    public Object gettingByIdentifier(int identifier) {
        Object object;
        if (tasks.containsKey(identifier)) {
            object = tasks.get(identifier);
        } else if (epics.containsKey(identifier)) {
            object = epics.get(identifier);
        } else if (subTasks.containsKey(identifier)) {
            object = subTasks.get(identifier);
        } else {
            System.out.println("По этому идентификатору ничего не найдено");
            object = null;
            return null;
        }
        return object;
    }

    // Создание задач и эпиков
    public void addTask(String taskName, String taskDescription) {
        taskID++;
        tasks.put(taskID, new Task(taskName, taskDescription, taskID));
    }

    public void addEpic(String taskName, String taskDescription) {
        taskID++;
        epics.put(taskID, new Epic(taskName, taskDescription, taskID));
    }

    public void addSubTask(String taskName, String taskDescription, int epicId) {
        if (epics.isEmpty()) {
            System.out.println("Нет эпиков куда записать подзадачу. В начале создайте эпик");
        } else {
            if (epics.containsKey(epicId) && epicId != keyEpics) {
                taskID++;
                Epic epic = epics.get(epicId);
                epic.getSubTasksId().add(taskID);
                subTasks.put(taskID, new SubTask(taskName, taskDescription, taskID));
                epic.setStatus(epic.checkEpicStatus(epic, subTasks)); //Обновления статуса эпика
            } else {
                System.out.println("Нет эпика по данному идентификатору.");
            }

        }

    }

    // Обновление задач
    public void updateTask(Task newTask) {
        int Id = newTask.getTaskId();
        if (tasks.containsKey(Id)) {
            tasks.remove(Id);
            tasks.put(Id, newTask);
        } else if (epics.containsKey(Id)) {
            deleteTaskByIdentificator(Id); // Эпик обновляется соответственно подзадачи удаляются.
            epics.put(Id, new Epic(newTask));
        } else if (subTasks.containsKey(Id)) {
            subTasks.remove(Id);
            subTasks.put(Id, new SubTask(newTask));
            for (Epic epic : epics.values()) {
                List<Integer> subTasksID = new ArrayList<>(epic.getSubTasksId());
                for (int subID : subTasksID) {
                    if (subID == Id) {
                        epic.setStatus(Epic.checkEpicStatus(epic, subTasks));
                    }
                }
            }
        } else {
            System.out.println("Нет такого идентификатора, попробуйте еще раз.");
        }
    }

    public List<SubTask> getEpicSubTask(int tasksID) {
        List<SubTask> subTask = new ArrayList<>();
        if (epics.containsKey(tasksID)) {
            Epic epic = epics.get(tasksID);
            for (int keySubTask : epic.getSubTasksId()) {
                subTask.add(subTasks.get(keySubTask));
            }
        } else {
            System.out.println("Нет такого идентификатора для эпика, попробуйте еще раз.");
            subTask = null;
        }
        return subTask;
    }

    // удаление по индетификатору
    public void deleteTaskByIdentificator(int tasksID) {
        if (tasks.containsKey(tasksID)) {
            tasks.remove(tasksID);
        } else if (epics.containsKey(tasksID)) {
            Epic epic = epics.get(tasksID);
            List<Integer> subTasksID = new ArrayList<>(epic.getSubTasksId());
            for (int subID : subTasksID) {
                subTasks.remove(subID); // Удаляем подзадачи они не существуют отдельно от эпика
            }
            epics.remove(tasksID);
        } else if (subTasks.containsKey(tasksID)) {
            subTasks.remove(tasksID);
            for (Epic epic : epics.values()) {
                List<Integer> subTasksID = new ArrayList<>(epic.getSubTasksId());
                for (int subID : subTasksID) {
                    if (subID == tasksID) {
                        epic.getSubTasksId().remove(Integer.valueOf(tasksID));
                        epic.setStatus(Epic.checkEpicStatus(epic, subTasks));
                    }
                }
            }
        }
    }

    // Выбор пользователем статуса задачи
    public TaskStatus selectTaskStatus(int command) {
        status = null;
        while (status == null) {
            switch (command) {
                case 1:
                    status = TaskStatus.NEW;
                    break;
                case 2:
                    status = TaskStatus.IN_PROGRESS;
                    break;
                case 3:
                    status = TaskStatus.DONE;
                    break;
                default:
                    System.out.println("Неверный ввод попробуйте еще раз");
                    break;
            }
        }
        return status;
    }
}