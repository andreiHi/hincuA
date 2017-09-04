package ru.job4j.tracker.start;
import ru.job4j.tracker.models.Item;

import java.util.Arrays;
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
    private Item[] items = new Item[100];
    /**
     * Позиция в хранилище.
     */
    private int pozition = 0;
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
        this.items[pozition++] = item;
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
            for (int i = 0; i !=  pozition; i++) {
                if (this.items[i].getId().equals(item.getId())) {
                    this.items[i] = item;
                    break;
                }
            }
        }
    }
    /**
     * Удаление заявок.
     * @param item item.
     */
    public void delete(Item item) {
        if (item != null) {
            for (int i = 0; i != pozition; i++) {
                if (this.items[i].getId().equals(item.getId())) {
                    this.items[i] = this.items[pozition - 1];
                    this.items[pozition - 1] = null;
                    this.pozition--;
                    break;
                }
            }
        }
    }
    /**
     * Получение списка всех заявок.
     * @return массив заявок.
     */
    public Item[] findAll() {
        Item[] items = new Item[pozition];
        for (int i = 0; i != pozition; i++) {
            items[i] = this.items[i];
        }
        return items;
    }
    /**
     * Получение списка по имени.
     * @param key key.
     * @return item.
     */
    public Item[] findByName(String key) {
        int count = 0;
        Item[] items = new Item[pozition];
        for (int i = 0; i != pozition; i++) {
            if (this.items[i] != null && this.items[i].getName().equals(key)) {
                items[count] = this.items[i];
                count++;
            }
        }
        return Arrays.copyOf(items, count);
    }
    /**
     * Получение заявки по id.
     * @param id id.
     * @return item.
     */
    public Item findById(String id) {
        Item item = null;
        if (id != null) {
            for (int i = 0; i != pozition; i++) {
                if (this.items[i] != null && this.items[i].getId().equals(id)) {
                    item = this.items[i];
                    break;
                }
            }
        }
        return item;
    }

}
