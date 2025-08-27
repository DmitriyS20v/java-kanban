package ru.yandex.javacourse.finaltask5;

import ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1.InMemoryTaskManager;
import ru.yandex.javacourse.finaltask5.managers.taskmanager.relise1.TaskManager;
import ru.yandex.javacourse.finaltask5.tasks.Epic;
import ru.yandex.javacourse.finaltask5.tasks.SubTask;
import ru.yandex.javacourse.finaltask5.tasks.Task;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        TaskManager manager = new InMemoryTaskManager();
        final int ID_TASK_1 = 8;
        final int ID_TASK_2 = 3;
        final int ID_EPIC_1 = 34;
        final int ID_EPIC_2 = 54;
        final int ID_SUBTACK_1 = 37;
        final int ID_SUBTACK_2 = 45;
        final int ID_SUBTACK_3 = 78;

        Task task = new Task("Помыть автомобиль", "Поехать на мойку и помыть автомобиль", ID_TASK_1);
        Task task2 = new Task("Купить телефон", "Выбрать телефон подходящий по характеристикам", ID_TASK_2);
        Epic epic = new Epic("Закупки", "Составить список товаров для покупки домой", ID_EPIC_1);
        Epic epic2 = new Epic("Повысить уровень своих знаний", "Продумать что требуется изучить " +
                "в зависимости от задач в этом году", ID_EPIC_2);
        SubTask subTask = new SubTask("Список задач", "Составить список задач, " +
                "решение которых требует дополнительных знаний", ID_SUBTACK_1, ID_EPIC_2);
        SubTask subTask2 = new SubTask("Список книг",
                "Составить список книг для прочтения", ID_SUBTACK_2, ID_EPIC_2);
        SubTask subTask3 = new SubTask("Найти статьи", "Просмотреть статьи интернете " +
                "по требуемым темам", ID_SUBTACK_3, ID_EPIC_2);

        manager.addTasks(task);
        manager.addTasks(task2);
        manager.addTasks(epic);
        manager.addTasks(epic2);
        manager.addTasks(subTask);
        manager.addTasks(subTask2);
        manager.addTasks(subTask3);

        manager.gettingByIdentifier(ID_SUBTACK_2);
        manager.gettingByIdentifier(ID_TASK_2);
        manager.gettingByIdentifier(ID_EPIC_1);
        manager.gettingByIdentifier(ID_SUBTACK_3);
        manager.gettingByIdentifier(ID_TASK_1);
        manager.gettingByIdentifier(ID_EPIC_2);
        manager.gettingByIdentifier(ID_SUBTACK_1);
        manager.gettingByIdentifier(ID_SUBTACK_2);
        manager.gettingByIdentifier(ID_SUBTACK_3);
        manager.gettingByIdentifier(ID_TASK_1);
        manager.gettingByIdentifier(ID_EPIC_2);
        manager.gettingByIdentifier(ID_SUBTACK_1);
        manager.gettingByIdentifier(ID_TASK_2);

        List<Task> tasks = manager.getHistory();

        for (Task newTask : tasks) {
            System.out.println(newTask);
            System.out.println();
        }

        manager.deleteTaskByIdentificator(ID_TASK_2);

        tasks = manager.getHistory();

        System.out.println("-".repeat(50));

        for (Task newTask : tasks) {
            System.out.println(newTask);
            System.out.println();
        }

        manager.deleteTaskByIdentificator(ID_EPIC_2);

        tasks = manager.getHistory();

        System.out.println("-".repeat(50));

        for (Task newTask : tasks) {
            System.out.println(newTask);
            System.out.println();
        }
    }
}

