package ru.yandex.javacourse.finaltask5.managers.historymanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.finaltask5.managers.historymanager.HistoryManager;

import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.javacourse.finaltask5.tasks.Task;
import ru.yandex.javacourse.finaltask5.tasks.Epic;
import ru.yandex.javacourse.finaltask5.tasks.SubTask;

import java.util.List;

public class InMemoryHistoryManagerTest {
    private static HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
        Task t = new Task("Бег трусцой", "Встать в 6 утра и выйти на пробежку", 35);
        Epic e = new Epic("Построить дом", "Спланировать строительство дома", 53);
        SubTask sub = new SubTask("Выбор дома", "Выбрать оптимальны дом, по вкусу, цене, и качеству.",
                57, 53);
        SubTask sub2 = new SubTask("Выбор строительной бригады"
                , "Посмотреть бригады по цене - качеству", 63, 53);
        historyManager.add(t);
        historyManager.add(e);
        historyManager.add(sub);
        historyManager.add(sub2);
    }

    @Test
    void addToHistory() {
        Task t2 = new Task("Постричься", "Записаться в парихмахерскую на стрижку", 84);
        historyManager.add(t2);

        List<Task> history = historyManager.getHistory();

        assertEquals(history.size(), 5);

    }

    @Test
    void removeFromHistory() {

        historyManager.remove(63);
        historyManager.remove(35);

        List<Task> history = historyManager.getHistory();

        assertEquals(history.size(), 2);
    }

    @Test
    void getHistoryAndCheckPosition() {

        Epic e = new Epic("Построить дом", "Спланировать строительство дома", 53);

        SubTask sub2 = new SubTask("Выбор строительной бригады"
                , "Посмотреть бригады по цене - качеству", 63, 53);

        List<Task> history = historyManager.getHistory();

        assertEquals(history.get(3), sub2, "Задача не совпадает.");

        assertEquals(history.get(1), e, "Задача не совпадает.");
    }
}
