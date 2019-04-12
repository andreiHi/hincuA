package ru.job4j.tracker.storage;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.12.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class TrackerMemory implements ITracker {
    private static final Logger LOG = LogManager.getLogger(TrackerMemory.class);

    private List<Item> items = new ArrayList<>();
    private AtomicInteger integer = new AtomicInteger(0);

    @Override
    public Item add(Item item) {
        this.items.add(item);
        item.setId(String.valueOf(integer.getAndIncrement()));
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        AtomicBoolean result = new AtomicBoolean(false);
        IntStream.range(0, this.items.size())
                .filter(i -> items.get(i).getId().equals(id))
                .findFirst()
                .ifPresent(
                        i -> {
                            result.set(true);
                            items.set(i, item);
                        }
                );
        return result.get();
    }
}
