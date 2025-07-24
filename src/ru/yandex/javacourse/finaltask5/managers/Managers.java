package ru.yandex.javacourse.finaltask5.managers;

import ru.yandex.javacourse.finaltask5.managers.historymanager.HistoryManager;
import ru.yandex.javacourse.finaltask5.managers.historymanager.InMemoryHistoryManager;
import ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1.InMemoryTaskManager;
import ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1.TaskManager;

public final class Managers {

    private Managers() {
    }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
