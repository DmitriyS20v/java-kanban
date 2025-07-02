
import java.util.HashMap;


public class TaskManager {
    private static int taskID = 0;
    private static TaskStatus status;
    public static final HashMap<Integer, Task> tasks = new HashMap<>();
    public static final HashMap<Integer, Epic> epics = new HashMap<>();
    public static final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private static final int keyEpics = 0; //Используется для тестирования при добавлении подзадачи

    public static void main(String[] args) {
        /* Весь код в методе main, я написал для проверки. Если я правильно понял задание,то должен был создать
        безконсольный функционал приложения.*/
        addTask("Поехать на отдых", "Встать в 6:00 и выехать в дорогу.");
        addEpic("Улучшить физическую форму", "Выполнять спортивные упражнения по " +
                "утрам");
        int key = taskID; // Пользователь вводит идентификатор эпика в который хочет добавить подзадачу.
        addSubTask("Отжимания", "3 подхода по 20 раз", key);
        addSubTask("Подтягивания", "3 подхода по 10 раз", key);
        key = 0;
        addEpic("Ремонт автомобиля", "Не корректно работает мотор");
        key = taskID;
        addSubTask("Диагностика автомобиля", "Отвезти машину в сервисный центр.", key);
        key = 0;
        printTasks();
        int count = 30;
        char symbol = '-';
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();
        key = 4; //Пользователь выбирает идентификатор подзадачи которую нужно обновить.
        int selectStatus = 2; // Пользователь выбирает статус задачи которую обновляет.
        status = selectTaskStatus(selectStatus);
        Task task = new Task("Отжимания", "3 подхода по 20 раз", status);
        updateTask(key, task);
        key = 1; //Изменил значения переменных для тестирования.
        selectStatus = 3;
        status = selectTaskStatus(selectStatus);
        task = new Task("Поехать на отдых", "Встать в 6:00 и выехать в дорогу.", status);
        updateTask(key, task);
        printTasks();
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();
        key = 5;
        getEpicSubTask(key);
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();
        key = 1;
        gettingByIdentifier(key);
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();
        key = 1;
        deleteTaskByIdentificator(key);
        key = 5;
        deleteTaskByIdentificator(key);
        printTasks();
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();
        key = 4;
        deleteTaskByIdentificator(key);
        printTasks();
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();
        deleteTask();
        printTasks();


    }


    // Метод для вывода всех задач с идентификатором
    public static void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Задачи отсутствуют.");
        } else {
            for (int keyTask : tasks.keySet()) {
                System.out.println("Id № " + keyTask + ".\nЗадача\n" + tasks.get(keyTask));
            }
        }
        if (epics.isEmpty()) {
            System.out.println("Эпики отсутствуют");
        } else {  /*Решил что под каждым эпиком должны быть выведены подзадачи, подзадачи не могут существовать
                    отдельно без эпиков*/
            for (int keyEpic : epics.keySet()) {
                Epic epic = epics.get(keyEpic);
                System.out.println("Id № " + keyEpic + ".\nЭпик\n" + epic);
                int hash = epic.hashCode();
                for (int keySubTask : subTasks.keySet()) {
                    SubTask subTask = subTasks.get(keySubTask);
                    int hashEpic = subTask.getHashCodeEpic();
                    if (hashEpic == hash) {
                        System.out.println("Id № " + keySubTask + ".\nПодзадача эпика\n" + subTask);
                    }
                }
            }
        }
    }


    // Удаление всех задач и обнуление идентификатора
    public static void deleteTask() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
        taskID = 0;
    }

    //Получение любой из подзадач указанием идентификатора
    public static void gettingByIdentifier(int identifier) {
        if (tasks.containsKey(identifier)) {
            System.out.println(tasks.get(identifier));
        } else if (epics.containsKey(identifier)) {
            System.out.println(epics.get(identifier));
        } else if (subTasks.containsKey(identifier)) {
            System.out.println(subTasks.get(identifier));
        } else {
            System.out.println("По этому идентификатору ничего не найдено");
        }
    }

    // Создание задач и эпиков
    public static void addTask(String taskName, String taskDescription) {
        taskID++;
        tasks.put(taskID, new Task(taskName, taskDescription));
    }

    public static void addEpic(String taskName, String taskDescription) {
        taskID++;
        epics.put(taskID, new Epic(taskName, taskDescription));
    }

    public static void addSubTask(String taskName, String taskDescription, int epicId) {
        if (epics.isEmpty()) {
            System.out.println("Нет эпиков куда записать подзадачу. В начале создайте эпик");
        } else {
            if (epics.containsKey(epicId) && epicId != keyEpics) {
                taskID++;
                Epic epic = epics.get(epicId);
                int hashCode = epic.hashCode();
                subTasks.put(taskID, new SubTask(taskName, taskDescription, hashCode));
                epic.setStatus(epic.checkEpicStatus(epic, subTasks)); //Обновления статуса эпика
            } else {
                System.out.println("Нет эпика по данному идентификатору.");
            }

        }

    }

    // Обновление задач
    public static void updateTask(int tasksID, Task newTask) {
        if (tasks.containsKey(tasksID)) {
            tasks.remove(tasksID);
            tasks.put(tasksID, newTask);
        } else if (epics.containsKey(tasksID)) {
            deleteTaskByIdentificator(tasksID); // Эпик обновляется соответсвенно подзадачи удаляются.
            epics.put(tasksID, new Epic(newTask));
        } else if (subTasks.containsKey(tasksID)) {
            int hashCode = subTasks.get(tasksID).getHashCodeEpic(); //Сох
            subTasks.remove(tasksID);
            subTasks.put(tasksID, new SubTask(newTask, hashCode));
            for (Epic epic : epics.values()) {
                int hash = epic.hashCode();
                if (hash == hashCode) {
                    //Обновления статуса эпика, подзадача обновилась
                    epic.setStatus(epic.checkEpicStatus(epic, subTasks));
                }
            }
        } else {
            System.out.println("Нет такого идентификатора, попробуйте еще раз.");
        }
    }

    public static void getEpicSubTask(int tasksID) {
        if (epics.containsKey(tasksID)) {
            Epic epic = epics.get(tasksID);
            int hashCode = epic.hashCode();
            for (int keySubTask : subTasks.keySet()) {
                SubTask subTask = subTasks.get(keySubTask);
                if (subTask.getHashCodeEpic() == hashCode) {
                    System.out.println("Id № " + keySubTask + ".\nПодзадача эпика\n" + subTask);
                }
            }
        } else {
            System.out.println("Нет такого идентификатора для эпика, попробуйте еще раз.");
        }

    }

    // удаление по индетификатору
    public static void deleteTaskByIdentificator(int tasksID) {
        if (tasks.containsKey(tasksID)) {
            tasks.remove(tasksID);
        } else if (epics.containsKey(tasksID)) {
            Epic epic = epics.get(tasksID);
            int hashCode = epic.hashCode();
            // Удаляем подзадачи они не существуют отдельно от эпика
            for (int keySubTask : subTasks.keySet()) {
                int hash = subTasks.get(keySubTask).getHashCodeEpic();
                if (hash == hashCode) {
                    subTasks.remove(keySubTask);
                }
            }
            epics.remove(tasksID);
        } else if (subTasks.containsKey(tasksID)) {
            int hashCode = subTasks.get(tasksID).getHashCodeEpic();
            subTasks.remove(tasksID);
            for (Epic epic : epics.values()) {
                int hash = epic.hashCode();
                if (hash == hashCode) {
                    //Обновление статуса эпика, подзадача была удалена
                    epic.setStatus(epic.checkEpicStatus(epic, subTasks));
                } else {
                    System.out.println("Задач по данному идентификатору не найдено.");
                }
            }


        }
    }

    // Выбор пользователем статуса задачи
    public static TaskStatus selectTaskStatus(int command) {
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