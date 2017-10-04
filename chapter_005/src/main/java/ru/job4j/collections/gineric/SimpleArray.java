package ru.job4j.collections.gineric;

/**
 * SimpleArray.
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.10.2017.
 * @version $Id$.
 * @since 0.1.
 *  Доделать контейнер SimpleArray<T> добавить методы add, update, delete, get(int index).+
 *  @param <T> тип данных которые могут быть в массиве.
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
     * @param size размер массива.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Метод добовляет новый элемент в массив.
     * @param value новый элемент.
     */
    public void add(T value) {
        objects[index++] = value;
    }

    /**
     * Метод получает значение по индексу в массиве.
     * @param pozition позиция в массиве.
     * @return значение зранимое в этой ячейке.
     */
    public T getValue(int pozition) {
        return (T) this.objects[pozition];
    }

    /**
     * Метод обновляет позицию в массиве.
     * @param index номер ячейки.
     * @param value новое значение.
     */
    public void update(int index, T value) {
        objects[index] = value;
    }

    /**
     * Метод удаляет заданный элемент.
     * @param value элемент.
     */
    public void delete(T value) {
        for (int i = 0; i < index; i++) {
            if (value.equals(objects[i])) {
                objects[i] = objects[index -1];
                objects[index-1] = null;
                this.index--;
            }
        }
    }
}
