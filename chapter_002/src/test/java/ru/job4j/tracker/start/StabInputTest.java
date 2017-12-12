package ru.job4j.tracker.start;

import org.junit.Test;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.09.17;
 * @version $Id$
 * @since 0.1
 */
public class StabInputTest {
    /**
     * Тест добавления нового элемента.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUi(input, tracker).init();
        //   создаём StartUI и вызываем метод init()
        tracker = new Tracker();
        Item item = tracker.getAll().get(0);
        assertThat(item.getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        tracker.delete(item);
        tracker.close();
    }

    /**
     * Тест метода edit изменяем существующий элемент.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUi(input, tracker).init();
        tracker = new Tracker();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
        tracker.delete(item);
        tracker.close();
    }

    /**
     *Тест метода showAll.
     */
    @Test
    public void whenTrackerHasItemsThenShowAll() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDesc1");
        Item item1 = new Item("test2", "testDesc2");
        tracker.add(item);
        tracker.add(item1);
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test1"));
        assertThat(tracker.findById(item1.getId()).getName(), is("test2"));
    }

    /**
     * Тест метода deleteItem.
     */
    @Test
    public void whetTrackerHasTwoItemsThenHasOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDesc1");
        Item item1 = new Item("test2", "testDesc2");
        tracker.add(item);
        tracker.add(item1);
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.getAll().get(0).getName(), is("test2"));
    }

    /**
     * Test findById.
     */
    @Test
    public void whenTrackerHasTwoItemThenReturnById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDesc1");
        Item item1 = new Item("test2", "testDesc2");
        tracker.add(item);
        tracker.add(item1);
        Input input = new StubInput(new String[]{"4", item1.getId(), "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findById(item1.getId()).getName(), is("test2"));
    }

    /**
     * Test findByName.
     */
    @Test
    public void whenTrackerHasTwoItemThenReturnByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDesc1");
        Item item1 = new Item("test2", "testDesc2");
        tracker.add(item);
        tracker.add(item1);
        Input input = new StubInput(new String[]{"5", item1.getName(), "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findByName(item1.getName()).get(0).getName(), is("test2"));
    }
}
