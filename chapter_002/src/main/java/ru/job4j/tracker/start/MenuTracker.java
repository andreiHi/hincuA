package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;
/**
 * Внешний не статический класс для редактирования элементов.
 */
class EditItem extends BaseAction {
    /**
     * Конструктор класса.
     * @param name Имя подменю.
     * @param key номер подменю.
     */
     EditItem(String name, int key) {
        super(name, key);
    }
    /**
     * key.
     * @return key.
     */
    @Override
    public int key() {
        return 2;
    }

    /**
     * Метод обновляет запись.
     * @param tracker tracker.
     * @param input input.
     */
    @Override
    public void execute(Tracker tracker, Input input) {
        String id = "";
        boolean found = false;
        Item item = null;
        while (!found) {
            id = input.ask("Enter id :");
            item = tracker.findById(id);
            if ("exit".equalsIgnoreCase(id)) {
                break;
            }
            if (item == null) {
                System.out.println("Invalid ID enter again or tape exit.");
            } else {
                found = true;
                String name = input.ask("Enter name :");
                String desc = input.ask("Enter description :");
                tracker.update(new Item(id, name, desc));
            }
        }
    }
}
/**
 * MenuTracker.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 07.09.17;
 * @version $Id$
 * @since 0.1
 */

public class MenuTracker {
    /**
     * Input.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     * Массив действий пользователя.
     */
    private UserAction[] actions = new UserAction[7];
    /**
     * номер позиции.
     */
    private int pozition = 0;
    /**
     * Конструктор класса.
     * @param input input.
     * @param tracker tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Заполняет массив действий всеми возможными действиями.
     */
    public void fillActions() {
        this.actions[pozition++] = new AddItem("Add Item.", 0);
        this.actions[pozition++] = new MenuTracker.ShowAllItems("Show all Items.", 1);
        this.actions[pozition++] = new EditItem("Edit item.", 2);
        this.actions[pozition++] = new DeleteItem("Delete Item.", 3);
        this.actions[pozition++] = new MenuTracker.FindById("Find by Id.", 4);
        this.actions[pozition++] = new FindByName("Find by Name.", 5);
        this.actions[pozition++] = new Exit("Exit.", 7);

    }

    /**
     * Метод возвращает массив с номерами возможных действий.
     * @return keys.
     */
    public int[] rangs() {
        int[] rang = new int[pozition];
        for (int i = 0; i < pozition; i++) {
            rang[i] = i;
        }
        return rang;
    }

    /**
     * Метод добовляет новое действие в меню.
     * @param action действие.
     */
    public void addAction(UserAction action) {
        this.actions[pozition++] = action;
    }

    /**
     * Выводит информацию об действии.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * В зависимости от ключа выполняет соответствующее действие.
     * @param key ключ.
     */
    public void select(int key) {
        this.actions[key].execute(this.tracker, this.input);
    }

    /**
     * Внутренний класс для добоавления элементов.
     */
    private class AddItem extends BaseAction {
        /**
         * Конструктор класса.
         * @param name имя подменю.
         * @param key номер подменю.
         */
        AddItem(String name, int key) {
            super(name, key);
        }

        /**
         * Ключь.
         * @return key.
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Исполнение добовления.
         * @param tracker tracker.
         * @param input input.
         */
        @Override
        public void execute(Tracker tracker, Input input) {
            String name = input.ask("Enter name :");
            String desc = input.ask("Enter description :");
            tracker.add(new Item(name, desc));
        }
    }

    /**
     * Класс описывает поведение при показе всех элементов.
     */
    private static class ShowAllItems extends BaseAction {
        /**
         * Конструктор класса.
         * @param name имя подменю.
         * @param key номер подменю.
         */
        ShowAllItems(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Tracker tracker, Input input) {
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s, %s, %s.", item.getId(), item.getName(), item.getDesc()));
            }
        }
    }

    /**
     * Класс удаления заявки.
     */
    private class DeleteItem extends BaseAction {
        /**
         * Конструктор класса.
         * @param name имя подменю.
         * @param key номер подменю.
         */
        DeleteItem(String name, int key) {
            super(name, key);
        }

        /**
         * key.
         * @return key.
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * Метод удаляет заявку.
         * @param tracker tracker.
         * @param input input.
         */
        @Override
        public void execute(Tracker tracker, Input input) {
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
    }

    /**
     * Класс поиска по ID.
     */
    public static class FindById extends BaseAction {
        /**
         * Конструктор класса.
         * @param name имя подменю.
         * @param key номер подменю.
         */
        FindById(String name, int key) {
            super(name, key);
        }

        /**
         * key.
         * @return key.
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * Метод ищет запись по id.
         * @param tracker tracker.
         * @param input input.
         */
        @Override
        public void execute(Tracker tracker, Input input) {
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
            input.writeMessage(String.format("%s, %s, %s", item.getId(), item.getName(), item.getDesc()));
        }
    }

    /**
     * Класс поиска по имени заявки.
     */
    private class FindByName extends BaseAction {
        /**
         * Конструктор класса.
         * @param name имя подменю.
         * @param key номер подменю.
         */
        FindByName(String name, int key) {
            super(name, key);
        }

        /**
         * key.
         * @return key.
         */
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Tracker tracker, Input input) {
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
            for (Item item1 : item) {
                input.writeMessage(String.format("%s, %s,%s", item1.getId(), item1.getName(), item1.getDesc()));
            }
        }
    }

    /**
     * Exit.
     */
    private class Exit extends BaseAction {
        /**
         * Конструктор класса.
         * @param name имя подменю.
         * @param key номер подменю.
         */
        Exit(String name, int key) {
            super(name, key);
        }

        /**
         * key.
         * @return key.
         */
        @Override
        public int key() {
            return 6;
        }

        /**
         *Exit.
         * @param tracker tracker.
         * @param input input.
         */
        @Override
        public void execute(Tracker tracker, Input input) {
            input.writeMessage("Tracker shutdown. Goodbye!");
        }
    }
}