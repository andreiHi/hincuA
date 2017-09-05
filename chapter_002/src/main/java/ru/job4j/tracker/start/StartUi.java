package ru.job4j.tracker.start;
import ru.job4j.tracker.models.Item;
import java.util.Arrays;
/**
 *StartUi .
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.09.17;
 * @version $Id$
 * @since 0.1
 */
public class StartUi {
    /**
     * Menu.
     */
    private final String menu = "0. Add new Item\n"
           + "1. Show all items\n"
           + "2. Edit item\n"
           + "3. Delete item\n"
           + "4. Find item by Id\n"
           + "5. Find items by name\n"
           + "6. Exit Program\n"
           + "Select:";
    /**
     * Exit.
     */
    private static final String EXIT = "6";
    /**
     * ADD.
     */
    private static final String ADD = "0";
    /**
     * Show all.
     */
    private static final String SHOW_ALL = "1";
    /**
     * Update item.
     */
    private static final String EDIT_ITEM = "2";
    /**
     * Delete item.
     */
    private static final String DELETE_ITEM = "3";
    /**
     * Find by id.
     */
    private static final String FIND_BY_ID = "4";
    /**
     * Find by name.
     */
    private static final String FIND_BY_NAME = "5";
    /**
     * Input.
     */
    private ConsoleInput input;
    /**
     * Tracker.
     */
    private Tracker tracker;

    /**
     * Конструктор с инициализацией.
     */
    public StartUi() {
        this.input = new ConsoleInput();
        this.tracker = new Tracker();
    }

    /**
     * Start.
     * @param args отсутствует.
     */
    public static void main(String[] args) {
        new StartUi().start();
    }

    /**
     * Главный метод программы.
     */
    public void start() {
        String ansver = "";
        while (true) {
            ansver = input.ask(menu);
            if (EXIT.equalsIgnoreCase(ansver)) {
                break;
            }
            switch (ansver) {
                case ADD : this.add(this.tracker, this.input);
                    break;
                case SHOW_ALL : this.showAll(this.tracker);
                    break;
                case EDIT_ITEM : this.editItem(this.tracker, this.input);
                    break;
                case DELETE_ITEM : this.deleteItem(tracker, input);
                    break;
                case FIND_BY_ID : this.findById(tracker, input);
                    break;
                case FIND_BY_NAME : this.findByName(tracker, input);
                    break;
                    default: continue;
            }

        }
    }

    /**
     * Метод добовляет заметку.
     * @param tracker tracker.
     * @param input input.
     */
    private void add(Tracker tracker, ConsoleInput input) {
        String name = input.ask("Enter name :");
        String desc = input.ask("Enter description :");
        tracker.add(new Item(name, desc));
    }

    /**
     * Метод выводит все заметки.
     * @param tracker tracker.
     */
    private void showAll(Tracker tracker) {
        System.out.println(Arrays.toString(tracker.findAll()));
    }

    /**
     * Метод обновляет заметку.
     * @param tracker tracker.
     * @param input input
     */
    private void editItem(Tracker tracker, ConsoleInput input) {
        String id = input.ask("Enter id :");
        tracker.update(tracker.findById(id));
    }

    /**
     * Метод удаляет заявку.
     * @param tracker tracker.
     * @param input input.
     */
    private void deleteItem(Tracker tracker, ConsoleInput input) {
        String id = input.ask("Enter id :");
        tracker.delete(tracker.findById(id));
    }

    /**
     * Метод находит заявку по id.
     * @param tracker tracker.
     * @param input input.
     */
    private void findById(Tracker tracker, ConsoleInput input) {
        String id = input.ask("Enter id :");
        Item item = tracker.findById(id);
        System.out.println(item);
    }

    /**
     * Метод находит все заявки с одинаковым именем.
     * @param tracker tracker.
     * @param input input.
     */
    private void findByName(Tracker tracker, ConsoleInput input) {
        String name = input.ask("Enter name :");
        Item[] item = tracker.findByName(name);
        System.out.println(Arrays.toString(item));
    }
}
