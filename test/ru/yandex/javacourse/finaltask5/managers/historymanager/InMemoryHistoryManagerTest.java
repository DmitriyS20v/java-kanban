package ru.yandex.javacourse.finaltask5.managers.historymanager;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.javacourse.finaltask5.tasks.Task;
import ru.yandex.javacourse.finaltask5.tasks.Epic;
import ru.yandex.javacourse.finaltask5.tasks.SubTask;

import java.util.List;

public class InMemoryHistoryManagerTest {
    private static HistoryManager historyManager;
    private static List<Task> history;
    private static Task task;
    private static Epic epic;
    private static SubTask subTask;
    private static SubTask subTask2;
    private static Task newTask;
    private static final int SIZE_HISTORY_BEFORE_ADD = 4;
    private static final int SIZE_HISTORY_AFTER_ADD = 5;
    private static final int SIZE_HISTORY_BEFORE_REMOVE = 4;
    private static final int SIZE_HISTORY_AFTER_REMOVE = 2;

    @BeforeAll
    public static void init() {
        task = new Task("Бег трусцой", "Встать в 6 утра и выйти на пробежку", 35);
        epic = new Epic("Построить дом", "Спланировать строительство дома", 53);
        subTask = new SubTask("Выбор дома", "Выбрать оптимальны дом, по вкусу, цене, и качеству.",
                57, 53);
        subTask2 = new SubTask("Выбор строительной бригады"
                , "Посмотреть бригады по цене - качеству", 63, 53);
        newTask = new Task("Постричься", "Записаться в парикмахерскую на стрижку", 84);
    }

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();

        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subTask);
        historyManager.add(subTask2);


    }

    @Test
    @DisplayName("Добавляем новую задачу: 4 было при добавлении еще одной станет 5")
    void test_AddToHistory_Result_5() {


        history = historyManager.getHistory();
        assertEquals(history.size(), SIZE_HISTORY_BEFORE_ADD);


        historyManager.add(newTask);

        history = historyManager.getHistory();
        assertEquals(history.size(), SIZE_HISTORY_AFTER_ADD);

    }

    @Test
    @DisplayName("В менеджере 4 задачи 2 удаляем останется 2")
    void test_RemoveFromHistory_Result_2() {

        history = historyManager.getHistory();
        assertEquals(history.size(), SIZE_HISTORY_BEFORE_REMOVE);

        historyManager.remove(63);
        historyManager.remove(35);


        history = historyManager.getHistory();
        assertEquals(history.size(), SIZE_HISTORY_AFTER_REMOVE);
    }

    @Test
    @DisplayName("Получаем список и проверяем порядок сохранения задач в Менеджер историй")
    void test_GetHistory_AndCheckPosition() {

        history = historyManager.getHistory();
        assertEquals(history.size(), SIZE_HISTORY_BEFORE_ADD);
        assertEquals(history.get(3), subTask2, "Задача не совпадает.");
        assertEquals(history.get(1), epic, "Задача не совпадает.");


        historyManager.add(newTask);

        history = historyManager.getHistory();
        assertEquals(history.size(), SIZE_HISTORY_AFTER_ADD);
        assertEquals(history.get(3), subTask2, "Задача не совпадает.");
        assertEquals(history.get(1), epic, "Задача не совпадает.");
        assertEquals(history.get(4), newTask, "Задача не совпадает.");
    }
}
