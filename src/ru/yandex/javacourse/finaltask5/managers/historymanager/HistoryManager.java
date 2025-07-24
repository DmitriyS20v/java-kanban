package ru.yandex.javacourse.finaltask5.managers.historymanager;

import ru.yandex.javacourse.finaltask5.tasks.Task;


import java.util.ArrayList;


public interface HistoryManager {

    <T extends Task> void add(T task);

    ArrayList<Task> getHistory();

}
