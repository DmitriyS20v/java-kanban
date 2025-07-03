package ru.yandex.javacourse.finaltask4;

import ru.yandex.javacourse.finaltask4.manager.TaskManager;
import ru.yandex.javacourse.finaltask4.tasks.Task;
import ru.yandex.javacourse.finaltask4.tasks.TaskStatus;


public class Main {
    public static TaskManager manager = new TaskManager();
    public static TaskStatus status;

    public static void main(String[] args) {
        /* Я постарался исправить все ошибки, проверку в методе main я оставил. Спасибо за указанные недочеты.
        Сложновато было все собрать т.к. код писался с нуля. Но очень познавательно) Спасибо!!!*/
        manager.addTask("Поехать на отдых", "Встать в 6:00 и выехать в дорогу.");
        manager.addEpic("Улучшить физическую форму", "Выполнять спортивные упражнения по " +
                "утрам");
        int key = 2; // Пользователь вводит идентификатор эпика в который хочет добавить подзадачу.
        manager.addSubTask("Отжимания", "3 подхода по 20 раз", key);
        manager.addSubTask("Подтягивания", "3 подхода по 10 раз", key);
        key = 0;
        manager.addEpic("Ремонт автомобиля", "Не корректно работает мотор");
        key = 5;
        manager.addSubTask("Диагностика автомобиля", "Отвезти машину в сервисный центр.", key);
        key = 0;
        System.out.println();
        System.out.println(manager.getTasks());
        System.out.println();
        System.out.println(manager.getEpics());
        System.out.println();
        System.out.println(manager.getSubTasks());
        System.out.println();
        int count = 30;
        char symbol = '-';
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();


        key = 4; //Пользователь выбирает идентификатор подзадачи которую нужно обновить.
        int selectStatus = 2; // Пользователь выбирает статус задачи которую обновляет.
        status = manager.selectTaskStatus(selectStatus);
        Task task = new Task("Отжимания", "3 подхода по 20 раз", status, key);
        manager.updateTask(task);
        key = 1; //Изменил значения переменных для тестирования.
        selectStatus = 3;
        status = manager.selectTaskStatus(selectStatus);
        task = new Task("Поехать на отдых", "Встать в 6:00 и выехать в дорогу.", status, key);
        manager.updateTask(task);
        System.out.println();
        System.out.println(manager.getTasks());
        System.out.println();
        System.out.println(manager.getEpics());
        System.out.println();
        System.out.println(manager.getSubTasks());
        System.out.println();
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();


        key = 2;
        System.out.println(manager.getEpicSubTask(key));
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();

        key = 1;
        System.out.println(manager.gettingByIdentifier(key));
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();


        key = 1;
        manager.deleteTaskByIdentificator(key);
        key = 5;
        manager.deleteTaskByIdentificator(key);
        System.out.println();
        System.out.println(manager.getTasks());
        System.out.println();
        System.out.println(manager.getEpics());
        System.out.println();
        System.out.println(manager.getSubTasks());
        System.out.println();
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();


        key = 4;
        manager.deleteTaskByIdentificator(key);
        System.out.println();
        System.out.println(manager.getTasks());
        System.out.println();
        System.out.println(manager.getEpics());
        System.out.println();
        System.out.println(manager.getSubTasks());
        System.out.println();
        System.out.println();
        System.out.println(String.valueOf(symbol).repeat(count));
        System.out.println();

        manager.deleteAllTask();
        manager.deleteAllSubTask();
        System.out.println();
        System.out.println(manager.getTasks());
        System.out.println();
        System.out.println(manager.getEpics());
        System.out.println();
        System.out.println(manager.getSubTasks());
        System.out.println();

        manager.deleteAllEpic();
        System.out.println();
        System.out.println(manager.getTasks());
        System.out.println();
        System.out.println(manager.getEpics());
        System.out.println();
        System.out.println(manager.getSubTasks());
        System.out.println();


    }
}
