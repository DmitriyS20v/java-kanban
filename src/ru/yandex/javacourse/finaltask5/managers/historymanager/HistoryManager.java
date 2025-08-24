package ru.yandex.javacourse.finaltask5.managers.historymanager;

import ru.yandex.javacourse.finaltask5.tasks.Task;

import java.util.List;


public interface HistoryManager {

    void add(Task task);

    void remove(int id);

    List<Task> getHistory();

}
