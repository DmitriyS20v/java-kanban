package ru.yandex.javacourse.finaltask5.managers.historymanager;

import ru.yandex.javacourse.finaltask5.tasks.Task;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node<Task>> nodes = new HashMap<Integer, Node<Task>>();

    private Node<Task> first;
    private Node<Task> last;

    private int size = 0;

    private static class Node<E extends Task> {
        public E task;
        public Node<E> next;
        public Node<E> prev;

        public Node(Node<E> prev, E task, Node<E> next) {
            this.task = task;
            this.next = next;
            this.prev = prev;
        }
    }


    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Задача не может быть пустой");
        } else {
            final int id = task.getTaskId();
            removeNode(id);
            linkLast(task);
            nodes.put(id, last);
        }
    }

    private void removeNode(int id) {
        if (nodes.containsKey(id)) {
            final Node<Task> node = nodes.get(id);
            if (node == null) return;
            nodes.remove(id);

            if (first == last) {
                first = null;
                last = null;
            } else if (node == first) {
                first = node.next;
                first.prev = null;
            } else if (node == last) {
                last = node.prev;
                last.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }
    }

    private void linkLast(Task task) {
        final Node<Task> oldLast = last;
        final Node<Task> newLast = new Node<Task>(oldLast, task, null);
        last = newLast;
        if (oldLast == null) {
            first = newLast;
        } else {
            oldLast.next = newLast;
        }
        size++;
    }

    @Override
    public void remove(int id) {
        removeNode(id);
    }

    @Override
    public List<Task> getHistory() {
        if (size == 0) {
            return Collections.emptyList();
        }
        List<Task> history = new ArrayList<>();

        Node<Task> node = first;
        while (node != null) {
            history.add(node.task);
            node = node.next;
        }

        return history;
    }

}
