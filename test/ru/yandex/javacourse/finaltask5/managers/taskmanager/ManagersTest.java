package ru.yandex.javacourse.finaltask5.managers.taskmanager;


import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.finaltask5.managers.Managers;
import ru.yandex.javacourse.finaltask5.managers.historymanager.HistoryManager;
import ru.yandex.javacourse.finaltask5.managers.historymanager.InMemoryHistoryManager;
import ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1.InMemoryTaskManager;
import ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1.TaskManager;
import ru.yandex.javacourse.finaltask5.tasks.Epic;
import ru.yandex.javacourse.finaltask5.tasks.Task;

import static org.junit.jupiter.api.Assertions.*;


public class ManagersTest {

    public static TaskManager manager = Managers.getDefault();
    public static HistoryManager historyManager = Managers.getDefaultHistory();


    @Test
    void managersReturnInitializedAndReadyToWorkInstanceOfManagers() {

        assertInstanceOf(InMemoryTaskManager.class, manager, "Должен возвращаться InMemoryTaskManager.");

        Task task1 = new Task("Поехать на отдых", "Встать в 6:00 и выехать в дорогу.", 1);
        Task task2 = new Task("Ремонт автомобиля", "Не корректно работает мотор", 2);
        Task task3 = new Task("Диагностика автомобиля", "Отвезти машину в сервисный центр.", 3);

        manager.addTasks(task1);
        manager.addTasks(task2);
        manager.addTasks(task3);

        assertEquals(3, manager.getTasks().size(), "Не соответствует количество задач.");

        assertInstanceOf(InMemoryHistoryManager.class, historyManager, "Должен возвращаться InMemoryHistoryManager.");

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);

        Task taskTest = new Task(historyManager.getHistory().get(2).getTaskName(),
                historyManager.getHistory().get(2).getTaskDescription(),
                historyManager.getHistory().get(2).getTaskId());

        assertEquals(task3, taskTest, "Сохраненная задача в менеджере историй не совпадает.");
    }

    @Test
    void checkAllClassFields() {
        Epic epic = new Epic("Диагностика автомобиля", "Отвезти машину в сервисный центр.", 3);

        manager.addTasks(epic);

        assertEquals(epic.getTaskName(), manager.getEpic(3).getTaskName(), "Имена эпика не совпадают");
        assertEquals(epic.getTaskDescription(), manager.getEpic(3).getTaskDescription(), "Расшифровка эпика не совпадает");
        assertEquals(epic.getTaskId(), manager.getEpic(3).getTaskId(), "Идентификатор эпика не совпадает");
    }

    @Test
    void historyManagerSavePreviosVersionTasks() {

        manager.addEpic("Тренировка", "Начать тренировки для улучшения спортивной формы");

        manager.getEpic(1);

        Epic epicVersion2 = new Epic("Тренировка", "Начать тренировки для улучшения здоровья", 1);

        manager.updateTask(epicVersion2);

        manager.getEpic(1);

        Epic epicVersion3 = new Epic("Тренировка", "Начать тренировки для качества жизни", 1);

        manager.updateTask(epicVersion3);

        manager.getEpic(1);

        assertEquals(1, manager.getHistory().size(), "Размер сохраненной истории просмотров эпиков не соответствует");


    }
}
