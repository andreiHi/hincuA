package ru.job4j.tracker.storage;

import ru.job4j.tracker.models.Item;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.12.2018.
 * @version $Id$.
 * @since 0.1.
 */
public interface ITracker {
    Item add(Item item);
    boolean replace(String id, Item item);
}
