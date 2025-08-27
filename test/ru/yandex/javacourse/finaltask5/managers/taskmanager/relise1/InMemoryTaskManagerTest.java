package ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.finaltask5.tasks.Epic;
import ru.yandex.javacourse.finaltask5.tasks.SubTask;
import ru.yandex.javacourse.finaltask5.tasks.Task;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private TaskManager taskManager;
    private static Task task;
    private static Task task2;
    private static Task task3;
    private static Epic epic;
    private static Epic epic2;
    private static SubTask subTask;
    private static SubTask subTask2;
    private static SubTask subTask3;
    private static SubTask subTask4;

    private static final int EMPTY_SIZE = 0;
    private static final int SIZE_ONE = 1;
    private static final int SIZE_TWO = 2;
    private static final int SIZE_THREE = 3;
    private static final int SIZE_FIVE = 5;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int THIRD_INDEX = 2;
    private static final int FOURTH_INDEX = 3;
    private static final int FIFTH_INDEX = 4;

    private static final int ID_NUMBER_1 = 2;
    private static final int ID_NUMBER_2 = 6;
    private static final int ID_NUMBER_3 = 10;
    private static final int ID_NUMBER_4 = 11;
    private static final int ID_NUMBER_5 = 17;
    private static final int ID_NUMBER_6 = 3;
    private static final int ID_NUMBER_7 = 4;
    private static final int ID_NUMBER_8 = 87;
    private static final int ID_NUMBER_9 = 12;

    @BeforeAll
    public static void initialize() {
        task = new Task("Уход за садом", "Полить цветы", ID_NUMBER_1);
        task2 = new Task("Сделать отчет", "Подготовить и сделать отчет по проделанной работе",
                ID_NUMBER_5);
        task3 = new Task("Купить овощей", "Купить, помидоры, огурцы, баклажан, капусту", ID_NUMBER_9);
        epic = new Epic("Пойти в поход", "Организовать поход", ID_NUMBER_2);
        epic2 = new Epic("Сделать ремонт комнаты", "Составить план действий по ремонту комнаты",
                ID_NUMBER_6);
        subTask = new SubTask("Собрать вещи для похода", "Составить список вещей для похода",
                ID_NUMBER_3, ID_NUMBER_2);
        subTask2 = new SubTask("Выбрать маршрут",
                "Составить несколько вариантов маршрута и выбрать их", ID_NUMBER_4, ID_NUMBER_2);
        subTask3 = new SubTask("Список", "Составить список материалов для ремонта",
                ID_NUMBER_7, ID_NUMBER_6);
        subTask4 = new SubTask("Взять еду", "Составить список необходимых продуктов для похода",
                ID_NUMBER_8, ID_NUMBER_2);

    }

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    @DisplayName("Проверяем добавление Task в менеджер. Получение его из менеджера и сохранение в объект.Проверка идентичности объектов" +
            "Проверка количества Task в менеджере")
    void test_instancesOfTheTaskClass_AreEqualById() {

        Task newTask = null;
        List<Task> tasks = taskManager.getTasks();
        assertEquals(tasks.size(), EMPTY_SIZE, "Список должен быть пуст");

        taskManager.addTasks(task);
        newTask = taskManager.getTask(ID_NUMBER_1);
        tasks = taskManager.getTasks();


        assertNotNull(newTask, "Задача не найдена");
        assertEquals(task, newTask, "Задачи не совпадают.");
        assertNotNull(taskManager.getTasks(), "Список задач пуст.");
        System.out.println(tasks);
        assertEquals(SIZE_ONE, tasks.size(), "Количество задач не совпадет.");
        assertEquals(task, tasks.get(FIRST_INDEX), "Задачи не совпадают.");
    }

    @Test
    @DisplayName("Проверяем добавление Epic в менеджер. Получение его из менеджера и сохранение в объект.Проверка идентичности объектов" +
            "Проверка количества Epic в менеджере")
    void test_instancesOfTheEpicClass_AreEqualById() {

        Epic newEpic = null;
        List<Epic> epics = taskManager.getEpics();
        assertEquals(epics.size(), EMPTY_SIZE, "Список должен быть пуст");

        taskManager.addTasks(epic);
        newEpic = taskManager.getEpic(ID_NUMBER_2);
        epics = taskManager.getEpics();

        assertNotNull(newEpic, "Задача не найдена");
        assertEquals(epic, newEpic, "Задачи не совпадают.");
        assertNotNull(epics, "Список задач пуст.");
        System.out.println(epics);
        assertEquals(SIZE_ONE, epics.size(), "Количество задач не совпадет.");
        assertEquals(epic, epics.get(FIRST_INDEX), "Задачи не совпадают.");
    }

    @Test
    @DisplayName("Проверяем добавление Epic в менеджер. Получение его из менеджера и сохранение в объект.Проверка идентичности объектов" +
            "Проверка количества Epic в менеджере")
    void test_instancesOfTheSubTaskClass_AreEqualById() {

        SubTask newSubTask = null;
        taskManager.addTasks(epic);
        List<SubTask> subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), EMPTY_SIZE, "Список должен быть пуст");

        taskManager.addTasks(subTask);
        newSubTask = taskManager.getSubTask(ID_NUMBER_3);
        subTasks = taskManager.getSubTasks();

        assertNotNull(newSubTask, "Задача не найдена");
        assertEquals(subTask, newSubTask, "Задачи не совпадают.");
        assertNotNull(subTasks, "Список задач пуст.");
        System.out.println(subTasks);
        assertEquals(SIZE_ONE, subTasks.size(), "Количество задач не совпадет.");
        assertEquals(subTask, subTasks.get(FIRST_INDEX), "Задачи не совпадают.");
    }

    @Test
    @DisplayName("Проверка может ли эпик сохраниться в подзадачу")
    void test_objectEpicCannotBeAdded_ToItselfAsASubtask() {

        Epic newEpic;
        List<Epic> epics = taskManager.getEpics();
        assertEquals(epics.size(), EMPTY_SIZE, "Список должен быть пуст");

        taskManager.addTasks(epic2);
        newEpic = taskManager.getEpic(ID_NUMBER_6);
        assertNotNull(newEpic, "Эпик должен быть добавлен в хеш таблицу");

        taskManager.addTasks(newEpic);
        SubTask newSubTask = taskManager.getSubTask(ID_NUMBER_6);
        assertNull(newSubTask, "Ошибка, эпик не может быть своей же подзадачей");

    }

    @Test
    @DisplayName("Проверка может ли подзадача сохраниться в эпик")
    void test_objectSubtaskCannotBeMade_ItsOwnEpic() {

        SubTask newSubTask = null;
        taskManager.addTasks(epic);
        List<SubTask> subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), EMPTY_SIZE, "Список должен быть пуст");

        taskManager.addTasks(subTask2);
        newSubTask = taskManager.getSubTask(ID_NUMBER_4);
        assertNotNull(newSubTask, "Задача не найдена");


        taskManager.addTasks(newSubTask);
        Epic newEpic = taskManager.getEpic(ID_NUMBER_4);
        assertNull(newEpic, "Ошибка, подзадача не может быть своим же эпиком");
    }

    @Test
    @DisplayName("Добавление разных типов задач и проверка на их наличие в менеджере")
    void test_addingDifferentTasks_AndGettingById() {

        List<Task> tasks = taskManager.getTasks();
        assertEquals(tasks.size(), EMPTY_SIZE, "Список должен быть пуст");
        List<Epic> epics = taskManager.getEpics();
        assertEquals(epics.size(), EMPTY_SIZE, "Список должен быть пуст");
        List<SubTask> subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), EMPTY_SIZE, "Список должен быть пуст");

        taskManager.addTasks(task2);
        taskManager.addTasks(epic2);
        taskManager.addTasks(subTask3);

        assertEquals(task2, taskManager.getTask(ID_NUMBER_5), "Сохраненная и найденная задачи не совпадают");
        assertEquals(epic2, taskManager.getEpic(ID_NUMBER_6), "Сохраненный и найденный эпики не совпадают");
        assertEquals(subTask3, taskManager.getSubTask(ID_NUMBER_7), "Сохраненная и найденная подзадачи не совпадают");

    }

    @Test
    @DisplayName("Добавляем в менеджер задачу, эпик и подзадачу. Проверяем сгенерированный идентификатор")
    void test_checkGiven_AndGeneratedId() {
        List<Task> tasks = taskManager.getTasks();
        assertEquals(tasks.size(), EMPTY_SIZE, "Список должен быть пуст");
        List<Epic> epics = taskManager.getEpics();
        assertEquals(epics.size(), EMPTY_SIZE, "Список должен быть пуст");
        List<SubTask> subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), EMPTY_SIZE, "Список должен быть пуст");

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
    @DisplayName("Создаём эпик с тремя подзадачами, удаляем одну из них, проверяем есть ли идентификатор задачи в списке эпика")
    void test_checkIdNotActualTask_InEpic() {

        List<Epic> epics = taskManager.getEpics();
        assertEquals(epics.size(), EMPTY_SIZE, "Список должен быть пуст");
        List<SubTask> subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), EMPTY_SIZE, "Список должен быть пуст");

        taskManager.addTasks(epic);
        taskManager.addTasks(subTask);
        taskManager.addTasks(subTask2);
        taskManager.addTasks(subTask4);

        epics = taskManager.getEpics();
        assertEquals(epics.size(), SIZE_ONE, "Должен быть один эпик");
        Epic test = taskManager.getEpic(ID_NUMBER_2);
        List<Integer> subTasksForEpic = test.getSubTasksId();

        assertTrue(subTasksForEpic.contains(subTask.getTaskId()), "Идентификатор отсутствует в эпике");
        assertTrue(subTasksForEpic.contains(subTask2.getTaskId()), "Идентификатор отсутствует в эпике");
        assertTrue(subTasksForEpic.contains(subTask4.getTaskId()), "Идентификатор отсутствует в эпике");

        taskManager.deleteTaskByIdentificator(ID_NUMBER_4);

        subTasksForEpic = test.getSubTasksId();
        assertFalse(subTasksForEpic.contains(ID_NUMBER_4), "Данная подзадача присутствует");
    }

    @Test
    @DisplayName("Добавить задачи в менеджер,проверить их наличие в менеджере. Проверить менеджер историй. Обновить задачи. " +
            "Проверить наличие обновленных задач и отсутствие старых версий в менеджере. Проверить менеджер историй после обновления задач")
    void test_checkDataInManager_AfterUpdateTasks_AndCheckHistoryManager_AfterUpdateTasks() {

        List<Task> tasks = taskManager.getTasks();
        assertEquals(tasks.size(), EMPTY_SIZE, "Список должен быть пуст");
        List<Epic> epics = taskManager.getEpics();
        assertEquals(epics.size(), EMPTY_SIZE, "Список должен быть пуст");
        List<SubTask> subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), EMPTY_SIZE, "Список должен быть пуст");
        List<Task> history = taskManager.getHistory();
        assertEquals(history.size(), EMPTY_SIZE, "Список должен быть пуст");

        taskManager.addTasks(task3);
        taskManager.addTasks(epic);
        taskManager.addTasks(subTask);
        taskManager.addTasks(subTask2);
        taskManager.addTasks(subTask4);


        //Строки ниже сделаны для того чтобы отобразить в истории просмотров
        Task getTask = taskManager.getTask(task3.getTaskId());
        Epic getEpic = taskManager.getEpic(epic.getTaskId());
        SubTask getSubTask = taskManager.getSubTask(subTask.getTaskId());
        SubTask getSubTask2 = taskManager.getSubTask(subTask2.getTaskId());
        SubTask getSubTask4 = taskManager.getSubTask(subTask4.getTaskId());

        tasks = taskManager.getTasks();
        assertEquals(tasks.size(), SIZE_ONE, "Количество сохраненных задач больше предполагаемого");
        assertEquals(task3, getTask, "Задача не совпадает");
        assertEquals(epic, getEpic, "Эпик не совпадает");
        assertEquals(subTask, getSubTask, "Подзадача не совпадает");
        assertEquals(subTask2, getSubTask2, "Подзадача не совпадает");
        assertEquals(subTask4, getSubTask4, "Подзадача не совпадает");
        tasks = taskManager.getTasks();
        assertEquals(tasks.size(), SIZE_ONE, "Размер списка задач должен быть 1");
        epics = taskManager.getEpics();
        assertEquals(epics.size(), SIZE_ONE, "Размер списка эпиков должен быть 1");
        subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), SIZE_THREE, "Размер списка подзадач должен быть 3");

        history = taskManager.getHistory();
        assertEquals(history.size(), SIZE_FIVE, "Размер истории должен быть 5");
        assertEquals(history.get(FIRST_INDEX).getTaskId(), getTask.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(SECOND_INDEX).getTaskId(), getEpic.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(FOURTH_INDEX).getTaskId(), getSubTask.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(FIFTH_INDEX).getTaskId(), getSubTask2.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(THIRD_INDEX).getTaskId(), getSubTask4.getTaskId(),
                "Идентификаторы объектов не совпадают");

        Task taskUpdate = new Task("Купить овощей", "Купить, помидоры, огурцы, баклажан, капусту, " +
                "картошку, свеклу", ID_NUMBER_9);

        taskManager.updateTask(taskUpdate);

        Task getTaskUpdate = taskManager.getTask(ID_NUMBER_9);

        tasks = taskManager.getTasks();
        assertEquals(tasks.size(), SIZE_ONE, "Количество сохраненных задач не соответствует предполагаемого");
        assertEquals(taskUpdate, getTaskUpdate, "Задача не совпадает");

        history = taskManager.getHistory();
        assertEquals(history.size(), SIZE_FIVE, "Размер истории должен быть 5");
        assertEquals(history.get(FIFTH_INDEX).getTaskId(), getTask.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(FIRST_INDEX).getTaskId(), getEpic.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(THIRD_INDEX).getTaskId(), getSubTask.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(FOURTH_INDEX).getTaskId(), getSubTask2.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(SECOND_INDEX).getTaskId(), getSubTask4.getTaskId(),
                "Идентификаторы объектов не совпадают");


        //Обновление подзадачи и проверки
        subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), SIZE_THREE, "Количество добавленных подзадач не совпадает с предполагаемым");

        SubTask subTaskUpdate = new SubTask("Собрать вещи для похода в горы",
                "Составить список вещей для похода и список одежды", ID_NUMBER_3, ID_NUMBER_2);
        taskManager.updateTask(subTaskUpdate);
        SubTask getSubTaskUpdate = taskManager.getSubTask(subTaskUpdate.getTaskId());


        subTasks = taskManager.getSubTasks();
        assertEquals(subTasks.size(), SIZE_THREE, "Количество добавленных подзадач не совпадает с предполагаемым");
        assertEquals(subTaskUpdate, getSubTaskUpdate, "Подзадача не совпадает");

        history = taskManager.getHistory();
        assertEquals(history.size(), SIZE_FIVE, "Размер истории должен быть 5");
        assertEquals(history.get(SECOND_INDEX).getTaskId(), getTask.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(FIRST_INDEX).getTaskId(), getEpic.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(FOURTH_INDEX).getTaskId(), getSubTask.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(FIFTH_INDEX).getTaskId(), getSubTask2.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(THIRD_INDEX).getTaskId(), getSubTask4.getTaskId(),
                "Идентификаторы объектов не совпадают");

        //Обновление эпика и проверки
        epics = taskManager.getEpics();
        assertEquals(epics.size(), SIZE_ONE, "Количество добавленных эпиков не совпадает с предполагаемым");

        Epic epicUpdate = new Epic("Поехать на отдых", "Организовать поезду на море", ID_NUMBER_2);
        taskManager.updateTask(epicUpdate);
        Epic getEpicUpdate = taskManager.getEpic(epicUpdate.getTaskId());

        epics = taskManager.getEpics();
        assertEquals(epics.size(), SIZE_ONE, "Количество добавленных эпиков не совпадает с предполагаемым");
        assertEquals(epicUpdate, getEpicUpdate, "Эпик не совпадает");

        history = taskManager.getHistory();
        assertEquals(history.size(), SIZE_TWO, "Размер истории должен быть 2");
        assertEquals(history.get(FIRST_INDEX).getTaskId(), getTask.getTaskId(),
                "Идентификаторы объектов не совпадают");
        assertEquals(history.get(SECOND_INDEX).getTaskId(), getEpic.getTaskId(),
                "Идентификаторы объектов не совпадают");

    }


}