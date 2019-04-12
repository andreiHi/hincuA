package ru.job4j.start;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.job4j.tracker.connection.ConnectionSQL;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.start.StartUi;
import ru.job4j.tracker.storage.TrackerDb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.09.17;
 * @version $Id$
 * @since 0.1
 */
public class StabInputTest {
    @Mock
    private TrackerDb trackerDb;
    private Item item;
    private Item res;
    @Before
    public void init() {
        this.item = new Item("test name", "desc");
        this.res = new Item("1", "test name", "desc");
        Mockito.when(trackerDb.add(item)).thenReturn(res);

    }
    /**
     * Тест добавления нового элемента.
     */
    @Test
    @Ignore
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        when(trackerDb.getAll()).thenReturn(new ArrayList<Item>(Arrays.asList(res)));
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUi(input, trackerDb).init();
        //   создаём StartUI и вызываем метод init()
   //     trackerDb = new TrackerDb();
        Item item = trackerDb.getAll().get(0);
        assertThat(item.getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        trackerDb.delete(item);
        trackerDb.close();
    }

    /**
     * Тест метода edit изменяем существующий элемент.
     */
    @Test
    @Ignore
    public void whenUpdateThenTrackerHasUpdatedValue() {
        //Напрямую добавляем заявку
        Item item1 = trackerDb.add(this.item);
        when(trackerDb.findById(item1.getId())).thenReturn(item1);
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item1.getId(), "test", "desc", "6"});
        new StartUi(input, trackerDb).init();
        verify(trackerDb).add(this.item);
        verify(trackerDb).findById(item1.getId());
        item1.setName("test");
        verify(trackerDb).replace(item1.getId(), item1);
        // создаём StartUI и вызываем метод init()

//        trackerDb = new TrackerDb();
//        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
//        assertThat(trackerDb.findById(item.getId()).getName(), is("test name"));
//        trackerDb.delete(item);
//        trackerDb.close();
    }


    /**
     * Тест метода deleteItem.
     */
    @Test
    @Ignore
    public void whetTrackerHasTwoItemsThenHasOneItem() {
        TrackerDb trackerDb = new TrackerDb(ConnectionSQL.getInstance().getConnection());
        Item item = new Item("test1", "testDesc1");
        Item item1 = new Item("test2", "testDesc2");
        item = trackerDb.add(item);
        item1 = trackerDb.add(item1);
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUi(input, trackerDb).init();
        trackerDb = new TrackerDb(ConnectionSQL.getInstance().getConnection());
        List<Item> list = new ArrayList<>();
        list.add(item1);
        assertThat(list, is(trackerDb.getAll()));
        trackerDb.delete(item);
        trackerDb.delete(item1);
        trackerDb.close();
    }

    /**
     * Test findById.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemThenReturnById() {
        TrackerDb trackerDb = new TrackerDb(ConnectionSQL.getInstance().getConnection());
        Item item = new Item("test1", "testDesc1");
        Item item1 = new Item("test2", "testDesc2");
        item = trackerDb.add(item);
        item1 = trackerDb.add(item1);
        Input input = new StubInput(new String[]{"4", item1.getId(), "3", "6"});
        new StartUi(input, trackerDb).init();
        trackerDb = new TrackerDb(ConnectionSQL.getInstance().getConnection());
        Item ex = trackerDb.findById(item.getId());
        assertThat(ex.getName(), is("test1"));
        trackerDb.delete(item);
        trackerDb.delete(item1);
        trackerDb.close();
    }

    /**
     * Test findByName.
     */
    @Test
    @Ignore
    public void whenTrackerHasTwoItemThenReturnByName() {
        TrackerDb trackerDb = new TrackerDb(ConnectionSQL.getInstance().getConnection());
        Item item = new Item("test1", "testDesc1");
        Item item1 = new Item("test2", "testDesc2");
        item = trackerDb.add(item);
        item1 = trackerDb.add(item1);
        Input input = new StubInput(new String[]{"5", item1.getName(), "6"});
        new StartUi(input, trackerDb).init();
        trackerDb = new TrackerDb(ConnectionSQL.getInstance().getConnection());
        List<Item> ex = new ArrayList<>(Arrays.asList(item1));
        assertThat(trackerDb.findByName(item1.getName()), is(ex));
        trackerDb.delete(item);
        trackerDb.delete(item1);
        trackerDb.close();
    }

    /**
     * Метод проверяет последовательность вызова методов с соответствующими аргументами при удалении заявки.
     */
    @Test
    @Ignore
    public void whenWasCallDeleteMethod() {
        Item add = this.trackerDb.add(this.item);
        when(trackerDb.findById(add.getId())).thenReturn(add);
        Input input = new StubInput(new String[]{"3", add.getId(), "6"});
        new StartUi(input, trackerDb).init();
        verify(trackerDb).add(item);
        verify(trackerDb).findById(add.getId());
        verify(trackerDb).delete(add);
        verify(trackerDb).close();
        verifyNoMoreInteractions(trackerDb);
    }

    /**
     * Тест проверяет последовательность вызова методов
     * при поиске заявки по id.
     */
    @Test
    @Ignore
    public void whenWasCallFindByIdMethod() {
        when(trackerDb.findById(res.getId())).thenReturn(res);
        Input input = new StubInput(new String[]{"4", res.getId(), "3", "6"});
        new StartUi(input, trackerDb).init();
        verify(trackerDb).findById(res.getId());
        verify(trackerDb).close();
        verifyNoMoreInteractions(trackerDb);
    }

    /**
     * Тест проверяет последовательность вызова методов с соответствующими аргументами
     * при вызове findByName метода.
     */
    @Test
    @Ignore
    public void whenWasCallFindByNameMethod() {
        when(trackerDb.findByName(res.getName())).thenReturn(new ArrayList<Item>(Arrays.asList(res)));
        Input input = new StubInput(new String[]{"5", res.getName(), "6"});
        new StartUi(input, trackerDb).init();
        verify(trackerDb).findByName(res.getName());
        verify(trackerDb).close();
        verifyNoMoreInteractions(trackerDb);
    }
}
