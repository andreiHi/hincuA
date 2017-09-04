package ru.job4j.tracker.start;

import org.junit.Test;
import ru.job4j.tracker.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class TrackerTest {
    /**
     * Test add new Item.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    /**
     * Test update.
     */
    @Test
    public void whenUpdateItemThenTrackerHasItemUpdate() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1",  "testDiscription", 123L);
        tracker.add(item);
        item.setDesc("труляля");
        tracker.update(item);
        assertThat("труляля", is(tracker.findAll()[0].getDesc()));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenTrackerHasTwoItemsThenDeletingHeHasOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDiscr", 123L);
        Item item2 = new Item("test2", "testDiscr", 123L);
        tracker.add(item);
        tracker.add(item2);
        tracker.delete(item);
        assertThat(tracker.findAll()[0], is(item2));
    }

    /**
     * Test findAll.
     */
    @Test
    public void whenTrackerHasTwoItemsThenFindAllReturnAll() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDiscr", 123L);
        Item item2 = new Item("test2", "testDiscr", 123L);
        tracker.add(item);
        tracker.add(item2);
        Item[] result = {item, item2};
        Item[] ex = tracker.findAll();
        assertThat(result, is(ex));
    }

    /**
     *Test findByName.
     */
    @Test
    public void whenTrackerHasTwoItemWithEqualsNameThenReturnThisItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDiscr", 123L);
        Item item2 = new Item("test1", "testDiscr", 123L);
        tracker.add(item);
        tracker.add(item2);
        Item[] result = {item, item2};
        Item[] ex =  tracker.findByName("test1");
        assertThat(result, is(ex));
    }

    /**
     * Test findById.
     */
    @Test
    public void whenTrackerHasTwoItemsAndFindByIdThenReturnItemById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDiscr", 123L);
        Item item2 = new Item("test1", "testDiscr", 123L);
        tracker.add(item);
        tracker.add(item2);
        String id = tracker.findAll()[0].getId();
        Item item1 = tracker.findById(id);
        assertThat(id, is(item1.getId()));
    }
}
