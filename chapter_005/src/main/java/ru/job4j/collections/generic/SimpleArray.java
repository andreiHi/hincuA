package ru.job4j.collections.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;

/**
 *SimpleArray .
 * Доделать контейнер SimpleArray<T> добавить методы addOrRemove, update, delete, get(int index).
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.10.17;
 * @version $Id$
 * @since 0.1
 * @param <T> параметризированный тип.
 */
public class SimpleArray<T> {
    /**
     * Массив.
     */
    private Object[]objects;
    /**
     * Позиция в массиве.
     */
    private int index = 0;

    /**
     * Конструктор класса.
     * @param size длинна массива.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Метод добавляет новые элеметы в массив.
     * @param value новый элемент.
     */
    public void add(T value) {
        objects[index++] = value;
    }

    /**
     * Метод обновляет значение по индексу.
     * @param id позиция в массиве.
     * @param value новое значение.
     */
    public boolean update(int id, T value) {
        boolean success = false;
        if (id < objects.length) {
            objects[id] = value;
            success = true;
        }
        return success;
    }

    @SuppressWarnings("unchecked")
    public T get(int i) {
        T t = null;
        if (i <= index) {
            t = (T) objects[i];
        }
        return t;
    }

    /**
     * Метод возвращает значение по индексу.
     * @param pozition позиция в массиве.
     * @return значение элемента.
     */
    @SuppressWarnings("unchecked")
    public T getValue(int pozition) {
        return (T) objects[pozition];
    }

    /**
     * Метод удаляет заданное значение из массива.
     * @param value  выбранное значение для удаления.
     * @return true or false.
     */
    public boolean delete(T value) {
        boolean found = false;
        for (int i = 0; i < objects.length; i++) {
            if (value.equals(objects[i])) {
                objects[i] = objects[index - 1];
                objects[index - 1] = null;
                this.index--;
                found = true;
                break;
            }
        }
        return found;

    }

    /**
     * Метод удаляет по индексу в массиве.
     * @param index позиция в массиве.
     * @return true or false.
     */
    public boolean delete(int index) {
        if (index > this.index) {
            throw new NoSuchElementException();
        }
        System.arraycopy(this.objects, index + 1, this.objects, index, this.objects.length - index - 1);
        return true;
    }
}
