package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;
/**
 * Внешний не статический класс для редактирования элементов.
 */
class EditItem implements UserAction {
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
     * Инфо о методе.
     * @return инфо.*
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item.");
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
     * @return возвращает массив с номерами возможных действий.
     */
    public int[] fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new MenuTracker.ShowAllItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new MenuTracker.FindById();
        this.actions[5] = new FindByName();
        this.actions[6] = new Exit();
        int[] rang = new int[this.actions.length];
        for (int i = 0; i < this.actions.length; i++) {
            rang[i] = i;
        }
        return rang;
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
    private class AddItem implements UserAction {
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

        /**
         * Информация о меню.
         * @return ключь и название меню.
         */
        @Override
        public String info() {
            return String.format("%s. %s.", this.key(), "Add new Item");
        }
    }

    /**
     * Класс описывает поведение при показе всех элементов.
     */
    private static class ShowAllItems implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Tracker tracker, Input input) {
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s, %s, %s.", item.getId(), item.getName(), item.getComments()));
            }
        }
        /**
         * info.
         * @return info.
         */
        @Override
        public String info() {
            return String.format("%s. %s.", this.key(), "Show all Items");
        }
    }

    /**
     * Класс удаления заявки.
     */
    private class DeleteItem implements UserAction {
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

        /**
         * info.
         * @return info.
         */
        @Override
        public String info() {
            return String.format("%s, %s", this.key(), "Delete Item.");
        }
    }

    /**
     * Класс поиска по ID.
     */
    public static class FindById implements UserAction {
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

        /**
         * info.
         * @return info.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find by ID.");
        }
    }

    /**
     * Класс поиска по имени заявки.
     */
    private class FindByName implements UserAction {
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

        /**
         * info.
         * @return info.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find by Name.");
        }
    }

    /**
     * Exit.
     */
    private class Exit implements UserAction {
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
        /**
         * info.
         * @return info.
         */
        @Override
        public String info() {
            return String.format("%s. %s.", this.key(), "Exit");
        }
    }
}
