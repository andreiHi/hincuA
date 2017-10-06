package ru.job4j.collections.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Простой контейнер на базе массива..
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.10.17;
 * @version $Id$
 * @since 0.1
 * @param <T> тип храннимых данных.
 */
public class SimpleContainer<T> implements Iterable<T> {
    /**
     * Хранилище.
     */
    private Object[]container;
    /**
     * Размер хранилища.
     */
    private int size = 10;
    /**
     * позиция в массиве.
     */
    private int pozition = 0;

    /**
     * Конструктор.
     * @param size размер.
     */
    public SimpleContainer(int size) {
        this.size = size;
        this.container = new Object[size];
    }

    /**
     * Конструктор с длинной массива 10 ячеек.
     */
    public SimpleContainer() {
        this.container = new Object[size];

    }

    /**
     * Итератор.
     * @return способ перебора массива.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return (T) container[currentIndex++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Добовление навых элементов с расширением массива при необходимости.
     * @param value добавляемый элемент.
     */
    public void add(T value) {
        if (pozition == size) {
            int newSize = this.size * 3 / 2 + 1;
            Object[]val = new Object[newSize];
            for (int i = 0; i < size; i++) {
                val[i] = container[i];
            }
            container = val;
            size = newSize;
        }
        container[pozition++] = value;

    }

    /**
     * Получение элемента по индексу в массиве.
     * @param index индекс.
     * @return элемент.
     */
    public T get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        } else {
            return (T) container[index];
        }
    }
}
