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
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;

    /**
     * Конструктор класса.
     * @param input взаимодействие с пользователем.
     * @param tracker трекер.
     */
    public StartUi(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Start.
     * @param args отсутствует.
     */
    public static void main(String[] args) {
        new StartUi(new ConsoleInput(), new Tracker()).init();
    }

    /**
     * Главный метод программы.
     */
    public void init() {
      MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
      menuTracker.fillActions();
      int key = 0;
      do {
          menuTracker.show();
           key = Integer.parseInt(input.ask("Select:"));
          menuTracker.select(key);
      } while (6 != key);

//        String ansver = "";
//        while (true) {
//            ansver = input.ask(menu);
//            if (EXIT.equalsIgnoreCase(ansver)) {
//                break;
//            }
//            switch (ansver) {
//                case ADD : this.add(this.tracker, this.input);
//                    break;
//                case SHOW_ALL : this.showAll(this.tracker);
//                    break;
//                case EDIT_ITEM : this.editItem(this.tracker, this.input);
//                    break;
//                case DELETE_ITEM : this.deleteItem(tracker, input);
//                    break;
//                case FIND_BY_ID : this.findById(tracker, input);
//                    break;
//                case FIND_BY_NAME : this.findByName(tracker, input);
//                    break;
//                default: continue;
//            }
//
//        }
    }

    /**
     * Метод добовляет заметку.
     * @param tracker tracker.
     * @param input input.
     */
    private void add(Tracker tracker, Input input) {
        String name = input.ask("Enter name :");
        String desc = input.ask("Enter description :");
        tracker.add(new Item(name, desc));
    }

    /**
     * Метод выводит все заметки.
     * @param tracker tracker.
     */
    private void showAll(Tracker tracker) {
        System.out.println(Arrays.toString(tracker.getAll()));
    }

    /**
     * Метод обновляет заметку.
     * @param tracker tracker.
     * @param input input
     */
    private void editItem(Tracker tracker, Input input) {
        String id = "";
        boolean found = false;
        Item item = null;
        while (!found) {
            id = input.ask("Enter id :");
            item = tracker.findById(id);
            if (item == null) {
                System.out.println("Invalid ID enter again.");
            } else {
                found = true;
            }
        }
        String name = input.ask("Enter name :");
        String desc = input.ask("Enter description :");
        tracker.update(new Item(id, name, desc));
    }

    /**
     * Метод удаляет заявку.
     * @param tracker tracker.
     * @param input input.
     */
    private void deleteItem(Tracker tracker, Input input) {
        String id = "";
        boolean found = false;
        Item item = null;
        while (!found) {
            id = input.ask("Enter id :");
            item = tracker.findById(id);
            if (item == null) {
                System.out.println("Invalid ID enter again.");
            } else {
                found = true;
            }
        }
        tracker.delete(item);
        input.writeMessage("The entry was successfully deleted.");
    }

    /**
     * Метод находит заявку по id.
     * @param tracker tracker.
     * @param input input.
     */
    private void findById(Tracker tracker, Input input) {
        String id = "";
        boolean found = false;
        Item item = null;
        while (!found) {
            id = input.ask("Enter id :");
            item = tracker.findById(id);
            if (item == null) {
                System.out.println("Invalid ID enter again.");
            } else {
                found = true;
            }
        }
        input.writeMessage(item.toString());
    }

    /**
     * Метод находит все заявки с одинаковым именем.
     * @param tracker tracker.
     * @param input input.
     */
    private void findByName(Tracker tracker, Input input) {
        String id = "";
        boolean found = false;
        Item[] item = null;
        while (!found) {
        String name = input.ask("Enter name :");
        item = tracker.findByName(name);
            if (item.length == 0) {
                System.out.println("Invalid name enter again.");
            } else {
                found = true;
            }
        }
        System.out.println(Arrays.toString(item));
    }
}
