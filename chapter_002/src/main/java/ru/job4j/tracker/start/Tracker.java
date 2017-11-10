package ru.job4j.tracker.start;
import ru.job4j.tracker.start.models.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Tracker {
    /**
     * Хранилище заявок.
     */
    //   private Item[] items = new Item[100];
    private ArrayList<Item> items1 = new ArrayList<>();
    /**
     * Рандом.
     */
    private static final Random RN = new Random();
    /**
     * Добавление заявок.
     * @param item - заявка.
     * @return - заявка.
     */
    public Item add(Item item) {
        item.setId(generateId());
        this.items1.add(item);
        return item;
    }
    /**
     * Генератор id.
     * @return id.
     */
    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
    /**
     * Pедактирование заявок.
     * @param item item.
     */
    public void update(Item item) {
        if (item != null) {
            int index = 0;
            for (Item item1 : this.items1) {
                if (item1.getId().equals(item.getId())) {
                    index = items1.indexOf(item1);
                    break;
                }
            }
            this.items1.set(index, item);
        }
    }

    /**
     * Удаление заявок.
     * @param item item.
     */
    public void delete(Item item) {
        if (item != null) {
            if (this.items1.contains(item)) {
                this.items1.remove(item);
            }
        }
    }
    /**
     * Получение списка всех заявок.
     * @return массив заявок.
     */
    public ArrayList<Item> getAll() {
        return items1;
    }
    /**
     * Получение списка по имени.
     * @param key key.
     * @return item.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> list = new ArrayList();
        for (Item item : items1) {
            if (item.getName().equals(key)) {
                list.add(item);
            }
        }
        return list;
    }
    /**
     * Получение заявки по id.
     * @param id id.
     * @return item.
     */
    public Item findById(String id) {
        Item item = null;
        if (id != null) {
            for (Item item1 : this.items1) {
                if (item1.getId().equals(id)) {
                    item = item1;
                }
            }
        }
        return item;
    }

}
