package ru.yandex.javacourse.finaltask5.managers.historymanager;

import ru.yandex.javacourse.finaltask5.tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_HISTORY_SIZE = 10;
    private final ArrayList<Task> browsingHistory = new ArrayList<>(MAX_HISTORY_SIZE);

    @Override
    public <T extends Task> void add(T task) {
        if (browsingHistory.size() == 10) {
            browsingHistory.remove(0);
            browsingHistory.add(task);
        } else {
            browsingHistory.add(task);
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return browsingHistory;
    }

}
