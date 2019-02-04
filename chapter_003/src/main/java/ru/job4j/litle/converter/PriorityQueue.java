package ru.job4j.litle.converter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.ListIterator;

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
        int index = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority() > task.getPriority()) {
            break;
            }
            index++;
        }
        tasks.add(index, task);
    }
    public void put0(Task task) {
        final ListIterator<Task> iterator = tasks.listIterator();
        int count = 0;
        while (iterator.hasNext()) {
            if (iterator.next().getPriority() > task.getPriority()) {
                break;
            }
            count++;
        }
        tasks.add(count, task);
    }

    public Task take() {
        return this.tasks.poll();
    }

    public void put2(Task task) {
        int index = (int) this.tasks.stream().filter(
                t -> task.getPriority() > t.getPriority()
        ).count();
        this.tasks.add(index, task);
    }
}
