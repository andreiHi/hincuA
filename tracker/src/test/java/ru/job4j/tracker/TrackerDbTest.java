package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.storage.TrackerDb;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class TrackerDbTest {
    private TrackerDb trackerDb;
    private Item item;



    public Connection init() {
        try (InputStream in = TrackerDb.class.getClassLoader().getResourceAsStream("tracker.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("user"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Test add new Item.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws SQLException {
        TrackerDb trackerDb = new TrackerDb(ConnectionRollback.create(this.init()));
        Item item1 = trackerDb.add(new Item("Test", "Desc"));
        assertThat(trackerDb.findByName("Test").size(), is(1));
    }

    /**
     * Test replace.
     */
    @Test
    @Ignore
    public void whenUpdateItemThenTrackerHasItemUpdate() {
        item = trackerDb.add(item);
        item.setDesc("newDescription");
        trackerDb.replace(item.getId(), item);
        Item fromTracer = trackerDb.findById(item.getId());
        assertThat("newDescription", is(fromTracer.getDesc()));
        trackerDb.delete(item);
    }

    /**
     * Test delete.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemsThenDeletingHeHasOneItem() {
        Item item = new Item("test1", "testDiscr");
        item = trackerDb.add(item);
        trackerDb.delete(item);
        item = trackerDb.findById(item.getId());
        assertThat(null, is(item));
    }

    /**
     * Test getAll.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemsThenFindAllReturnAll() {
        Item item = new Item("test1", "testDiscr");
        Item item2 = new Item("test2", "testDiscr");
        item = trackerDb.add(item);
        item2 = trackerDb.add(item2);
        ArrayList<Item> result = new ArrayList<>();
        result.add(item);
        result.add(item2);
        List<Item> ex = trackerDb.getAll();
        assertThat(result, is(ex));
        trackerDb.delete(item);
        trackerDb.delete(item2);
    }

    /**
     *Test findByName.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemWithEqualsNameThenReturnThisItems() {
        Item item = new Item("test1", "testDiscr");
        Item item2 = new Item("test1", "testDiscr");
        item = trackerDb.add(item);
        item2 = trackerDb.add(item2);
        ArrayList<Item> result = new ArrayList<>();
        result.add(item);
        result.add(item2);
        List<Item> ex =  trackerDb.findByName("test1");
        assertThat(result, is(ex));
        trackerDb.delete(item);
        trackerDb.delete(item2);
    }

    /**
     * Test findById.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemsAndFindByIdThenReturnItemById() {
        Item item = new Item("test1", "testDiscr");
        item = trackerDb.add(item);
        String id = item.getId();
        Item item2 = trackerDb.findById(id);
        assertThat(id, is(item2.getId()));
        trackerDb.delete(item);
    }
}
