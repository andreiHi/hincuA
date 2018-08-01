package ru.job4j.litle.converter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.LinkedList;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.07.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class PriorityQueue {
    private static final Logger LOG = LogManager.getLogger(PriorityQueue.class);
    private LinkedList<Task> tasks = new LinkedList<>();


    /**
     * Метод добавляет задачу по приоретету.
     *
     * @param task задача
     */
    public void put(Task task) {
        int index = tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority() > task.getPriority()) {
                index = i;
                break;
            }
        }
        tasks.add(index, task);
    }
    public void put1(Task task) {
        for (int i = 0; i <= tasks.size(); i++) {
            if (tasks.size() == i) {
                tasks.add(task);
                break;
            }
            if (tasks.get(i).getPriority() > task.getPriority()) {
                this.tasks.add(i, task);
                break;
            }
        }

    }

    public Task take() {
        return this.tasks.poll();
    }
}
