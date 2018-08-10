package ru.job4j.litle.converter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.07.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Task {
    private static final Logger LOG = LogManager.getLogger(Task.class);
    private int priority;
    private String desc;

    public Task(String desc, int priority) {
        this.priority = priority;
        this.desc = desc;
    }

    public int getPriority() {
        return priority;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
