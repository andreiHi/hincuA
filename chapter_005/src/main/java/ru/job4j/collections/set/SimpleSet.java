package ru.job4j.collections.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleSEt.
 *@param <E> type of date.
 * @author Hincu Andrei (andreih1981@gmail.com) by 11.10.17;
 * @version $Id$
 * @since 0.1
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     * хранилище.
     */
    private Object[] container;
    /**
     * длинна массива.
     */
    private int size;
    /**
     * позиция в массиве.
     */
    private int position = 0;

    /**
     * конструктор.
     */
    public SimpleSet() {
        this.size = 1;
    }

    /**
     * Итератор.
     * @return перебор массива.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) container[index++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Метод добовляет новые уникальные элементы в хранилище.
     * @param value добовляемый элемент.
     */
    public void add(E value) {
        if (container == null) {
            this.container = new Object[size];
        }
        boolean found = false;
        for (int i = 0; i < this.size; i++) {
            if (value.equals(container[i])) {
                found = true;
                break;
            }
        }
        if (!found) {
            if (position == size) {
                int newSize = this.size * 3 / 2 + 1;
                this.container = Arrays.copyOf(container, newSize);
                this.size = newSize;
            }
            this.container[position++] = value;
        }
    }
}
