package ru.job4j.collections.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс справочник.
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.10.2017.
 * @version $Id$.
 * @since 0.1.
 * @param <T> тип ключа.
 * @param  <V> тип значения.
 */
public class Catalog<T, V> implements Iterable {
    int size = 16;
    private Entry<T, V>[] table;
    private int pozition = 0;

    public Catalog() {
        this.table = new Entry[size];
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Entry<T, V> e;
            int index = 0;
            int i = 0;

            @Override
            public boolean hasNext() {
                return index < pozition;
            }

            @Override
            public Entry next() {
                if (hasNext()) {
                    for (; i < table.length; i++ ) {
                        if (table[i] != null) {
                            e = table[i];
                            index++;
                            break;
                        }
                    }
                    this.i =  i + 1;
                    return e;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    static class Entry<T, V> {
        int hash;
        T key;
        V val;

        public Entry(int hash, T key, V val) {
            this.hash = hash;
            this.key = key;
            this.val = val;
        }

        public T getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }
    }

    public boolean insert(T key, V value) {
        boolean success = false;
        if (size == pozition) {
            transfer(table);
        } else {
            int hash = hash(key.hashCode());
            int index = indexFor(hash, size);
            if (table[index] == null) {
                addNewEntry(hash, key, value, index, table);
                success = true;
            } else {
                Entry<T, V> oldElement = table[index];
                if (!oldElement.val.equals(value)) {
                    oldElement.val = value;
                    success = true;
                }
            }
        }
        return success;
    }

    private void transfer(Entry[] entry) {
        int newSize = this.size * 2;
        Entry[] newTable = new Entry[newSize];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry<T, V> element = table[i];
                if (element.hash == 0 && element.key == null) {
                    putForNullKey(newTable, element.val);
                } else {
                    int newIndex = indexFor(element.hash, newSize);
                    addNewEntry(element.hash, element.key, element.val, newIndex, newTable);
                }
            }
        }
        this.size = newSize;
        this.table = newTable;
    }
    public void addNewEntry(int hash, T key, V value, int index, Entry[]table) {
        Entry<T, V> newElement = new Entry<>(hash, key, value);
        table[index] = newElement;
        pozition++;
    }
    public void putForNullKey(Entry[]table, V val) {
        Entry<T, V> newNullKeyElement = new Entry<>(0, null, val);
        table[0] = newNullKeyElement;
        pozition++;
    }

    public V get(T key) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash, size);
        return table[index].val;
    }
    public boolean delete(T key) {
        boolean f = false;
        int hash = hash(key.hashCode());
        int index = indexFor(hash, size);
        if (table[index] != null) {
            table[index] = null;
            f = true;
        }
        return f;
    }
    /**
     * Метод hash(key) гарантирует что полученные хэш-коды,
     * будут иметь только ограниченное количество коллизий
     * (примерно 8, при дефолтном значении коэффициента загрузки).
     * @param h hashCode элемента.
     * @return хэш.
     */
    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Определяется позиция в массиве, куда будет помещен элемент.
     * @param h хэш из метода hash().
     * @param length длинна массива.
     * @return позиция в массиве.
     */
    static int indexFor(int h, int length) {
        return h & (length - 1);
    }
}
