package ru.job4j.tracker.start;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;

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
    @Ignore
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        Item item1 = tracker.add(item);
        String id = item1.getId();
        Item i = tracker.findById(id);
        assertThat(item1, is(i));
        tracker.delete(item1);
        tracker.close();
    }

    /**
     * Test update.
     */
    @Test
    @Ignore
    public void whenUpdateItemThenTrackerHasItemUpdate() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1",  "testDiscription");
        item = tracker.add(item);
        item.setDesc("newDescription");
        tracker.update(item);
        Item fromTracer = tracker.findById(item.getId());
        assertThat("newDescription", is(fromTracer.getDesc()));
        tracker.delete(item);
        tracker.close();
    }

    /**
     * Test delete.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemsThenDeletingHeHasOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDiscr");
        item = tracker.add(item);
        tracker.delete(item);
        item = tracker.findById(item.getId());
        assertThat(null, is(item));
        tracker.close();
    }

    /**
     * Test getAll.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemsThenFindAllReturnAll() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDiscr");
        Item item2 = new Item("test2", "testDiscr");
        item = tracker.add(item);
        item2 = tracker.add(item2);
        ArrayList<Item> result = new ArrayList<>();
                result.add(item);
                result.add(item2);
        List<Item> ex = tracker.getAll();
        assertThat(result, is(ex));
        tracker.delete(item);
        tracker.delete(item2);
        tracker.close();
    }

    /**
     *Test findByName.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemWithEqualsNameThenReturnThisItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDiscr");
        Item item2 = new Item("test1", "testDiscr");
        item = tracker.add(item);
        item2 = tracker.add(item2);
        ArrayList<Item> result = new ArrayList<>();
                result.add(item);
                result.add(item2);
        List<Item> ex =  tracker.findByName("test1");
        assertThat(result, is(ex));
        tracker.delete(item);
        tracker.delete(item2);
        tracker.close();
    }

    /**
     * Test findById.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemsAndFindByIdThenReturnItemById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDiscr");
        item = tracker.add(item);
        String id = item.getId();
        Item item2 = tracker.findById(id);
        assertThat(id, is(item2.getId()));
        tracker.delete(item);
        tracker.close();
    }
}
