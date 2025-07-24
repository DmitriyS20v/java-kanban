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


        taskManager.addTasks(epic);
        SubTask subTask = taskManager.getSubTask(7);
        assertNotEquals(epic, subTask, "Ошибка, эпик не может быть своей же подзадачей");

    }

    @Test
    void objectSubtaskCannotBeMadeItsOwnEpic() {
        Epic epic = new Epic("Пойти в поход", "Организовать поход", 7);
        taskManager.addTasks(epic);
        SubTask subTask = new SubTask("Выбрать маршрут",
                "Составить несколько вариантов маршрута и выбрать их", 11, 7);
        taskManager.addTasks(subTask);

        taskManager.addTasks(subTask);
        Epic epic2 = taskManager.getEpic(11);
        assertNotEquals(subTask, epic2, "Ошибка, подзадача не может быть своим же эпиком");
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


}