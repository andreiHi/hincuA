package ru.job4j.collections.list;

import java.util.Iterator;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.10.17;
 * @version $Id$
 * @since 0.1
 */
public class SimpleContainer<T>implements Iterable<T> {
    private Object[]container;
    int size = 10;
    int pozition;

    public SimpleContainer(int size) {
        this.size = size;
        this.container = new Object[size];
    }

    public SimpleContainer() {
        this.container = new Object[size];

    }

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
                return (T) container[currentIndex++];
            }
        };
    }
    public void add(T value){
        if (pozition == size){
            int newSize = this.size *3 / 2 + 1;
            Object[]val = new Object[newSize];
            for (int i = 0; i < size; i++) {
                val[i] = container[i];
            }
            container = val;
            size = newSize;
        }
        container[pozition++] = value;

    }
    public T get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        } else {
            return (T) container[index];
        }
    }
}
