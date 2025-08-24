package ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.finaltask5.tasks.Epic;
import ru.yandex.javacourse.finaltask5.tasks.SubTask;
import ru.yandex.javacourse.finaltask5.tasks.Task;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();


    }

    @Test
    void instancesOfTheTaskClassAreEqualById() {
        Task task = new Task("Уход за садом", "Полить цветы", 2);
        taskManager.addTasks(task);
        Task task2 = taskManager.getTask(2);
        assertNotNull(task2, "Задача не найдена");
        assertEquals(task, task2, "Задачи не совпадают.");

        List<Task> tasks = taskManager.getTasks();

        assertNotNull(taskManager.getTasks(), "Список задач пуст.");
        assertEquals(1, tasks.size(), "Количество задач не совпадет.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void instancesOfTheEpicClassAreEqualById() {
        Epic epic = new Epic("Пойти в поход", "Организовать поход", 6);
        taskManager.addTasks(epic);
        Epic epic2 = taskManager.getEpic(6);

        assertNotNull(epic2, "Задача не найдена");
        assertEquals(epic, epic2, "Задачи не совпадают.");

        List<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Список задач пуст.");
        System.out.println(epics);
        assertEquals(1, epics.size(), "Количество задач не совпадет.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");
    }

    @Test
    void instancesOfTheSubTaskClassAreEqualById() {
        Epic epic = new Epic("Пойти в поход", "Организовать поход", 7);
        taskManager.addTasks(epic);

        SubTask subTask = new SubTask("Собрать вещи для похода", "Составить список вещей для похода", 10, 7);
        taskManager.addTasks(subTask);
        SubTask subTask2 = taskManager.getSubTask(10);

        assertNotNull(subTask2, "Задача не найдена");
        assertEquals(subTask, subTask2, "Задачи не совпадают.");

        List<SubTask> subTasks = taskManager.getSubTasks();

        assertNotNull(subTasks, "Список задач пуст.");
        System.out.println(subTasks);
        assertEquals(1, subTasks.size(), "Количество задач не совпадет.");
        assertEquals(subTask, subTasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void objectEpicCannotBeAddedToItselfAsASubtask() {
        Epic epic = new Epic("Пойти в поход", "Организовать поход", 7);
        taskManager.addTasks(epic);
        Epic epic2 = taskManager.getEpic(7);
        assertNotNull(epic2, "Эпик должен быть добавлен в хеш таблицу");

        taskManager.addTasks(epic);
        SubTask subTask = taskManager.getSubTask(7);
        assertNull(subTask, "Ошибка, эпик не может быть своей же подзадачей");

    }

    @Test
    void objectSubtaskCannotBeMadeItsOwnEpic() {
        Epic epic = new Epic("Пойти в поход", "Организовать поход", 7);
        taskManager.addTasks(epic);
        SubTask subTask = new SubTask("Выбрать маршрут",
                "Составить несколько вариантов маршрута и выбрать их", 11, 7);
        taskManager.addTasks(subTask);
        SubTask subTask2 = taskManager.getSubTask(11);
        assertNotNull(subTask2, "Подзадача должна быть добавлена в хеш таблицу");

        taskManager.addTasks(subTask);
        Epic epic2 = taskManager.getEpic(11);
        assertNull(epic2, "Ошибка, подзадача не может быть своим же эпиком");
    }

    @Test
    void addingDifferentTasksAndGettingById() {
        Task task = new Task("Сделать отчет", "Подготовить и сделать отчет по проделанной работе", 1);
        Epic epic = new Epic("Сделать ремонт комнаты", "Составить план действий по ремонту комнаты", 3);
        SubTask subTask = new SubTask("Список", "Составить список материалов для ремонта", 4, 3);
        taskManager.addTasks(task);
        taskManager.addTasks(epic);
        taskManager.addTasks(subTask);

        assertEquals(task, taskManager.getTask(1), "Сохраненная и найденная задачи не совпадают");
        assertEquals(epic, taskManager.getEpic(3), "Сохраненный и найденный эпики не совпадают");
        assertEquals(subTask, taskManager.getSubTask(4), "Сохраненная и найденная подзадачи не совпадают");

    }

    @Test
    void checkGivenAndGeneratedId() {

        int taskId = 0;
        int epicId = 0;
        int subTaskId = 0;

        taskManager.addTask("Купить овощей", "Купить, помидоры, огурцы, баклажан, капусту");
        taskManager.addEpic("Продать автомобиль", "Выложить объявление на торговую площадку");
        taskManager.addSubTask("Выбрать сайты",
                "Подобрать наиболее актуальные сайты по продаже автомобиля", 2);

        for (Task task : taskManager.getTasks()) {
            if (task.getTaskName().equals("Купить овощей") &
                    task.getTaskDescription().equals("Купить, помидоры, огурцы, баклажан, капусту")) {
                taskId = task.getTaskId();
            }
        }

        for (Epic epic : taskManager.getEpics()) {
            if (epic.getTaskName().equals("Продать автомобиль") &
                    epic.getTaskDescription().equals("Выложить объявление на торговую площадку")) {
                epicId = epic.getTaskId();
            }
        }

        for (SubTask subTask : taskManager.getSubTasks()) {
            if (subTask.getTaskName().equals("Выбрать сайты") &
                    subTask.getTaskDescription().equals("Подобрать наиболее актуальные сайты по продаже автомобиля")) {
                subTaskId = subTask.getTaskId();
            }
        }

        Task task = new Task("Поездка на рыбалку", "Взять удочки и снасти", taskId);
        Epic epic = new Epic("Начало учебного года", "Подготовиться к учебному году", epicId);
        SubTask subTask = new SubTask("Материалы", "Изучить несколько книг по учёбе",
                subTaskId, epicId);

        taskManager.addTasks(task);
        taskManager.addTasks(epic);
        taskManager.addTasks(subTask);

        assertEquals(task, taskManager.getTask(taskId), "Задачи не совпадают.");
        assertEquals(epic, taskManager.getEpic(epicId), "Эпики не совпадают");
        assertEquals(subTask, taskManager.getSubTask(subTaskId), "Подзадачи не совпадают");

        for (Task taskGenerategId : taskManager.getTasks()) {
            if (taskGenerategId.getTaskName().equals("Купить овощей") &
                    taskGenerategId.getTaskDescription().equals("Купить, помидоры, огурцы, баклажан, капусту")) {
                taskId = taskGenerategId.getTaskId();
            }
        }

        for (Epic epicGeneratedId : taskManager.getEpics()) {
            if (epicGeneratedId.getTaskName().equals("Продать автомобиль") &
                    epicGeneratedId.getTaskDescription().equals("Выложить объявление на торговую площадку")) {
                epicId = epicGeneratedId.getTaskId();
            }
        }

        for (SubTask subTaskGeneratedId : taskManager.getSubTasks()) {
            if (subTaskGeneratedId.getTaskName().equals("Выбрать сайты") &
                    subTaskGeneratedId.getTaskDescription().equals("Подобрать наиболее актуальные сайты по продаже автомобиля")) {
                subTaskId = subTaskGeneratedId.getTaskId();
            }
        }

        assertEquals(4, taskId, "Задачи по сгенерированному идентификатору не совпадают");
        assertEquals(5, epicId, "Эпики по сгенерированному идентификатору не совпадают");
        assertEquals(6, subTaskId, "Подзадачи по сгенерированному идентификатору не совпадают");

    }

    @Test
    void checkIdNotActualTaskInEpic() {
        Epic epic = new Epic("Пойти в поход", "Организовать поход", 7);
        taskManager.addTasks(epic);

        SubTask subTask = new SubTask("Собрать вещи для похода", "Составить список вещей для похода", 10, 7);
        taskManager.addTasks(subTask);

        SubTask subTask2 = new SubTask("Составить маршрут", "Выбрать подходящий маршрут по погодным условиям.", 37, 7);
        taskManager.addTasks(subTask2);

        SubTask subTask3 = new SubTask("Взять еду", "Составить список необходимых продуктов для похода", 87, 7);
        taskManager.addTasks(subTask3);

        taskManager.deleteTaskByIdentificator(10);

        Epic test = taskManager.getEpic(7);
        List<Integer> subTasksForEpic = test.getSubTasksId();

        assertFalse(subTasksForEpic.contains(subTask.getTaskId()), "Данная подзадача присутствует");
    }

    @Test
    void checkDataInManagerAfterUpdateTasksAndCheckHistoryManagerAfterUpdateTasks() {

        Epic epic = new Epic("Пойти в поход", "Организовать поход", 7);
        taskManager.addTasks(epic);

        SubTask subTask = new SubTask("Собрать вещи для похода", "Составить список вещей для похода", 10, 7);
        taskManager.addTasks(subTask);

        SubTask subTask2 = new SubTask("Составить маршрут", "Выбрать подходящий маршрут по погодным условиям.", 37, 7);
        taskManager.addTasks(subTask2);

        SubTask subTask3 = new SubTask("Взять еду", "Составить список необходимых продуктов для похода", 87, 7);
        taskManager.addTasks(subTask3);

        Task task = new Task("Купить овощей", "Купить, помидоры, огурцы, баклажан, капусту", 12);
        taskManager.addTasks(task);


        //Строки ниже сделаны для того чтобы отобразить в истории просмотров
        Task getTask = taskManager.getTask(task.getTaskId());
        Epic getEpic = taskManager.getEpic(epic.getTaskId());
        SubTask getSubTask = taskManager.getSubTask(subTask.getTaskId());
        SubTask getSubTask2 = taskManager.getSubTask(subTask2.getTaskId());
        SubTask getSubTask3 = taskManager.getSubTask(subTask3.getTaskId());

        List<Task> tasks = taskManager.getTasks();
        assertEquals(tasks.size(), 1, "Количество сохраненных задач больше предполагаемого");
        assertEquals(task, getTask, "Задача не совпадает");
        assertEquals(epic, getEpic, "Эпик не совпадает");
        assertEquals(subTask, getSubTask, "Подзадача не совпадает");
        assertEquals(subTask2, getSubTask2, "Подзадача не совпадает");
        assertEquals(subTask3, getSubTask3, "Подзадача не совпадает");

        Task taskUpdate = new Task("Купить овощей", "Купить, помидоры, огурцы, баклажан, капусту, катошку, свеклу", 12);

        taskManager.updateTask(taskUpdate);

        Task getTaskUpdate = taskManager.getTask(taskUpdate.getTaskId());

        tasks = taskManager.getTasks();
        assertEquals(tasks.size(), 1, "Количество сохраненных задач не соответствует предполагаемого");
        assertEquals(taskUpdate, getTaskUpdate, "Задача не совпадает");
        assertEquals(epic, getEpic, "Эпик не совпадает");
        assertEquals(subTask, getSubTask, "Подзадача не совпадает");
        assertEquals(subTask2, getSubTask2, "Подзадача не совпадает");
        assertEquals(subTask3, getSubTask3, "Подзадача не совпадает");

        //Проверка списка истории на размер и соответствие измененной задачи
        List<Task> tasksHistory = taskManager.getHistory();
        assertEquals(tasksHistory.size(), 5, "Количество просмотренных задач не совпадает с предполагаемым");

        //Обновление подзадачи и проверки
        List<SubTask> subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), 3, "Количество добавленных подзадач не совпадает с предполагаемым");

        SubTask subTaskUpdate = new SubTask("Собрать вещи для похода в горы",
                "Составить список вещей для похода и список одежды", 10, 7);
        taskManager.updateTask(subTaskUpdate);
        SubTask getSubTaskUpdate = taskManager.getSubTask(subTaskUpdate.getTaskId());

        subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), 3, "Количество добавленных подзадач не совпадает с предполагаемым");
        assertEquals(subTaskUpdate, getSubTaskUpdate, "Подзадача не совпадает");

        tasksHistory = taskManager.getHistory();
        assertEquals(tasksHistory.size(), 5, "Количество просмотренных задач не совпадает с предполагаемым");
        assertTrue(tasksHistory.contains(getSubTaskUpdate), "Обновленная версия эпика не совпадает в истории просмотров");

        //Обновление эпика и проверки
        List<Epic> epics = taskManager.getEpics();
        assertEquals(epics.size(), 1, "Количество добавленных эпиков не совпадает с предполагаемым");

        Epic epicUpdate = new Epic("Поехать на отдых", "Организовать поезду на море", 7);
        taskManager.updateTask(epicUpdate);
        Epic getEpicUpdate = taskManager.getEpic(epicUpdate.getTaskId());

        epics = taskManager.getEpics();
        assertEquals(epics.size(), 1, "Количество добавленных эпиков не совпадает с предполагаемым");
        assertEquals(epicUpdate, getEpicUpdate, "Эпик не совпадает");

        tasksHistory = taskManager.getHistory();
        assertEquals(tasksHistory.size(), 2, "Количество просмотренных задач не совпадает с предполагаемым");
        assertTrue(tasksHistory.contains(getEpicUpdate), "Обновленная версия эпика не совпадает в истории просмотров");

    }


}