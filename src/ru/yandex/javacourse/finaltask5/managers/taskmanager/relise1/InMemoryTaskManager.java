package ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1;

import ru.yandex.javacourse.finaltask5.managers.Managers;
import ru.yandex.javacourse.finaltask5.managers.historymanager.HistoryManager;
import ru.yandex.javacourse.finaltask5.tasks.Epic;
import ru.yandex.javacourse.finaltask5.tasks.SubTask;
import ru.yandex.javacourse.finaltask5.tasks.Task;
import ru.yandex.javacourse.finaltask5.tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  // Добавил более гибкую реализацию хеш таблицам


public class InMemoryTaskManager implements TaskManager {
    private int taskID = 0;
    private TaskStatus status = null;


    public final Map<Integer, Task> tasks = new HashMap<>();
    public final Map<Integer, Epic> epics = new HashMap<>();
    public final Map<Integer, SubTask> subTasks = new HashMap<>();
    private final int key = 0; //Используется для тестирования при добавлении подзадачи
    public final HistoryManager historyManager = Managers.getDefaultHistory();


    // Получение списков
    // Т.к. если просматриваем полностью весь список подзадач, эпиков и задач то я решил что они тоже должны отображаться
    // в истории просмотров.
    @Override
    public List<SubTask> getSubTasks() {
        List<SubTask> subTask = new ArrayList<>();
        for (SubTask sub : subTasks.values()) {
            subTask.add(sub);
            historyManager.add(sub);
        }
        return subTask;
    }

    @Override
    public List<Epic> getEpics() {
        List<Epic> epic = new ArrayList<>();
        for (Epic e : epics.values()) {
            epic.add(e);
            historyManager.add(e);
        }
        return epic;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> task = new ArrayList<>();
        for (Task t : tasks.values()) {
            task.add(t);
            historyManager.add(t);
        }
        return task;
    }

    // Удаление всех задач и обнуление идентификатора
    @Override
    public void deleteAllTask() {
        for (int t : tasks.keySet()) {
            historyManager.remove(t);
        }
        tasks.clear();
    }

    @Override
    public void deleteAllEpic() {
        for (int e : epics.keySet()) {
            historyManager.remove(e);
        }
        epics.clear();
        for (int sub : subTasks.keySet()) {
            historyManager.remove(sub);
        }
        subTasks.clear(); // Если мы удаляем все эпики значит удаляем и подзадачи
    }

    @Override
    public void deleteAllSubTask() {
        for (Epic epic : epics.values()) {
            List<Integer> subTasksID = new ArrayList<>(epic.getSubTasksId());
            for (int subID : subTasksID) {
                subTasks.remove(subID);
                historyManager.remove(subID);
                epic.getSubTasksId().remove(Integer.valueOf(subID));
            }
            Epic.checkEpicStatus(epic, subTasks);
        }
    }

    //Получение задач по идентификатору, решил добавить и тут проверку на null.
    @Override
    public Task getTask(int taskID) {
        if (tasks.get(taskID) != null) historyManager.add(tasks.get(taskID));
        return tasks.get(taskID);
    }

    @Override
    public Epic getEpic(int epicID) {
        if (epics.get(epicID) != null) historyManager.add(epics.get(epicID));
        return epics.get(epicID);
    }

    @Override
    public SubTask getSubTask(int subTaskID) {
        if (subTasks.get(subTaskID) != null) historyManager.add(subTasks.get(subTaskID));
        return subTasks.get(subTaskID);
    }

    //Получение любой из подзадач указанием идентификатора, htibk e,hfnm ghjdthre yf null так это лишнее
    @Override
    public Object gettingByIdentifier(int identifier) {
        Object object;
        if (tasks.containsKey(identifier)) {
            historyManager.add(tasks.get(identifier));
            object = tasks.get(identifier);
        } else if (epics.containsKey(identifier)) {
            historyManager.add(epics.get(identifier));
            object = epics.get(identifier);
        } else if (subTasks.containsKey(identifier)) {
            historyManager.add(subTasks.get(identifier));
            object = subTasks.get(identifier);
        } else {
            System.out.println("По этому идентификатору ничего не найдено");
            return null;
        }
        return object;
    }


    @Override
    public void addTask(String taskName, String taskDescription) {
        taskID++;
        if (checkIdetificator(taskID)) {   //проверка на совпадение идентификатора
            taskID = getUniqueTaskID();    //генерация уникального идентификатора
            if (taskID > key) {  //проверка на случай переполнения количества задач
                tasks.put(taskID, new Task(taskName, taskDescription, taskID));  //запись задачи с уникальным идентификатором, если нашелся такой же идентификатор.
            } else {
                System.out.println("Превышено число возможных задач");
            }
        } else {
            tasks.put(taskID, new Task(taskName, taskDescription, taskID));  //запись задачи с уникальным идентификатором, если не нашлось такого же идентификатора
        }
    }

    public <T extends Task> void addTasks(T task) {
        if (task.getTaskName() == null) {
            System.out.println("Имя объекта отсутствует");
        } else if (task.getTaskDescription() == null) {
            System.out.println("Расшифровка задачи отсутствует");
        } else if (task.getTaskId() == 0) {
            System.out.println("Идентификатор задачи не может быть равен 0");
        } else {
            if (checkIdetificator(task.getTaskId())) {
                changeIdentificator(task.getTaskId());
                if (task instanceof Epic) {
                    if (getUniqueTaskID() > key) {
                        epics.put(task.getTaskId(), (Epic) task);
                    } else {
                        System.out.println("Превышено число возможных задач");
                    }
                } else if (task instanceof SubTask) {
                    if (((SubTask) task).getEpicId() == 0) {
                        System.out.println("Отсутствует идентификатор эпика.");
                    } else {
                        if (getUniqueTaskID() > key) {
                            addSubTask((SubTask) task);
                        } else {
                            System.out.println("Превышено число возможных задач");
                        }
                    }
                } else {
                    if (getUniqueTaskID() > key) { //проверка на случай переполнения количества задач
                        tasks.put(task.getTaskId(), task);
                    } else {
                        System.out.println("Превышено число возможных задач");
                    }
                }
            } else {
                if (task instanceof Epic) {
                    epics.put(task.getTaskId(), (Epic) task);
                } else if (task instanceof SubTask) {
                    addSubTask((SubTask) task);
                } else {
                    tasks.put(task.getTaskId(), task);
                }
            }
        }
    }


    @Override
    public void addEpic(String taskName, String taskDescription) {
        taskID++;
        if (checkIdetificator(taskID)) {
            taskID = getUniqueTaskID();
            if (taskID > key) {
                epics.put(taskID, new Epic(taskName, taskDescription, taskID));
            } else {
                System.out.println("Превышено число возможных задач");
            }
        } else {
            epics.put(taskID, new Epic(taskName, taskDescription, taskID));
        }

    }


    @Override
    public void addSubTask(String taskName, String taskDescription, int epicId) {
        if (epics.isEmpty()) {
            System.out.println("Нет эпиков куда записать подзадачу. В начале создайте эпик");
        } else if (epics.get(epicId).getTaskName().equals(taskName) & epics.get(epicId).getTaskDescription().equals(taskDescription) &
                epics.get(epicId).getTaskId() == epicId) {
            System.out.println("Нельзя добавить сам эпик, в себя в виде подзадачи");
        } else {
            if (epics.containsKey(epicId) && epicId > key) {
                taskID++;
                if (checkIdetificator(taskID)) {
                    taskID = getUniqueTaskID();
                    if (taskID > key) {
                        Epic epic = epics.get(epicId);
                        epic.getSubTasksId().add(taskID);
                        subTasks.put(taskID, new SubTask(taskName, taskDescription, taskID, epicId));
                        epic.setStatus(Epic.checkEpicStatus(epic, subTasks)); //Обновления статуса эпика
                    } else {
                        System.out.println("Превышено число возможных задач");
                    }
                } else {
                    Epic epic = epics.get(epicId);
                    epic.getSubTasksId().add(taskID);
                    subTasks.put(taskID, new SubTask(taskName, taskDescription, taskID, epicId));
                    epic.setStatus(Epic.checkEpicStatus(epic, subTasks));
                }
            } else {
                System.out.println("Нет эпика по данному идентификатору.");
            }
        }
    }

    //Вспомагательный метод для addTasks
    private void addSubTask(SubTask subTask) {
        int id = subTask.getTaskId();
        int epicId = subTask.getEpicId();
        if (epics.isEmpty()) {
            System.out.println("Нет эпиков куда записать подзадачу. В начале создайте эпик");
        } else {
            if (epics.containsKey(epicId) && epicId != key) {
                Epic epic = epics.get(epicId);
                epic.getSubTasksId().add(id);
                subTasks.put(id, subTask);
                epic.setStatus(Epic.checkEpicStatus(epic, subTasks));
                epics.put(epicId, epic);//Обновления статуса эпика


            } else {
                System.out.println("Нет эпика по данному идентификатору.");
            }
        }
    }

    // Обновление задач
    @Override
    public <T extends Task> void updateTask(T newTask) {
        int identificator = newTask.getTaskId();
        if (tasks.containsKey(identificator)) {
            tasks.remove(identificator);
            historyManager.remove(identificator);
            tasks.put(identificator, newTask);
        } else if (epics.containsKey(identificator)) {
            deleteTaskByIdentificator(identificator); // Эпик обновляется соответственно подзадачи удаляются.
            epics.put(identificator, new Epic(newTask));
        } else if (subTasks.containsKey(identificator)) {
            int epicId = subTasks.get(identificator).getEpicId();
            subTasks.remove(identificator);
            historyManager.remove(identificator);
            subTasks.put(identificator
                    , new SubTask(newTask, epicId));
            Epic epic = epics.get(epicId);
            epic.setStatus(Epic.checkEpicStatus(epic, subTasks));
            epics.put(epicId, epic);
        } else {
            System.out.println("Нет такого идентификатора, попробуйте еще раз.");
        }
    }


    @Override
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

    // удаление по идентификатору
    @Override
    public void deleteTaskByIdentificator(int tasksID) {
        if (tasks.containsKey(tasksID)) {
            tasks.remove(tasksID);
            historyManager.remove(tasksID);
        } else if (epics.containsKey(tasksID)) {
            Epic epic = epics.get(tasksID);
            List<Integer> subTasksID = new ArrayList<>(epic.getSubTasksId());
            for (int subID : subTasksID) {
                subTasks.remove(subID);// Удаляем подзадачи они не существуют отдельно от эпика
                historyManager.remove(subID);
            }
            epics.remove(tasksID);
            historyManager.remove(tasksID);
        } else if (subTasks.containsKey(tasksID)) {
            subTasks.remove(tasksID);
            historyManager.remove(tasksID);
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
    @Override
    public TaskStatus selectTaskStatus(int command) {
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

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    // Проверка на наличие существующего идентификатора задачи
    private boolean checkIdetificator(int identificator) {
        boolean result = tasks.containsKey(identificator) | epics.containsKey(identificator) |
                subTasks.containsKey(identificator);
        return result;
    }

    //Поиск и замена идентификатора у записанной в хеш таблицу задачи
    private void changeIdentificator(int identificator) {
        if (tasks.containsKey(identificator)) {
            changeIdentificatorTask(identificator);
        } else if (epics.containsKey(identificator)) {
            changeIdentificatorEpic(identificator);
        } else if (subTasks.containsKey(identificator)) {
            changeIdentificatorSubTask(identificator);
        }
    }

    //вспомогательный метод изменения идентификатора задачи
    private void changeIdentificatorTask(int identificator) {
        int uniqueId = getUniqueTaskID();
        if (tasks.containsKey(identificator)) {
            Task task = tasks.get(identificator);
            Task taskNewId = new Task(task.getTaskName(), task.getTaskDescription(), uniqueId);
            tasks.put(uniqueId, taskNewId);
        }
    }

    //вспомогательный метод изменения идентификатора эпика
    private void changeIdentificatorEpic(int identificator) {
        int uniqueId = getUniqueTaskID();
        if (epics.containsKey(identificator)) {
            Epic epic = epics.get(identificator);
            Epic epicNewId = new Epic(epic.getTaskName(), epic.getTaskDescription(), uniqueId);
            List<Integer> subTasksID = new ArrayList<>(epic.getSubTasksId());
            for (int subID : subTasksID) {
                SubTask subTask = new SubTask(subTasks.get(subID).getTaskName(), subTasks.get(subID).getTaskDescription(), subID, uniqueId);
                subTasks.put(subID, subTask);
            }
            epics.put(uniqueId, epicNewId);
        }
    }

    //вспомогательный метод изменения идентификатора подзадачи
    private void changeIdentificatorSubTask(int identificator) {
        int uniqueId = getUniqueTaskID();
        int epicId = subTasks.get(identificator).getEpicId();
        if (subTasks.containsKey(identificator)) {
            SubTask subTask = subTasks.get(identificator);
            SubTask subTaskNewId = new SubTask(subTask.getTaskName(), subTask.getTaskDescription(), uniqueId, subTask.getEpicId());
            subTasks.put(uniqueId, subTaskNewId);
            Epic epic = epics.get(epicId);
            epic.removeIdSubtaskInListSubtask(identificator);
            epic.getSubTasksId().add(uniqueId);
            epic.setStatus(Epic.checkEpicStatus(epic, subTasks));
            epics.put(epicId, epic);
        }
    }


    //вспомогательный метод для получения уникального идентификатора с проверкой на существующие
    private int getUniqueTaskID() {
        taskID = 0;
        while (true) {
            taskID++;

            if (!tasks.containsKey(taskID) &&
                    !epics.containsKey(taskID) &&
                    !subTasks.containsKey(taskID) &&
                    taskID != Integer.MAX_VALUE) {
                return taskID;
            } else if (taskID == Integer.MAX_VALUE) {
                System.out.println("Достигнуто максимальное количество задач");
                return taskID = 0;
            }
        }
    }
}


