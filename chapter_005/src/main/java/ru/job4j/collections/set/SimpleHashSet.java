package ru.job4j.collections.set;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 13.10.17;
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashSet<E> extends SimpleSet<E> {
      private Entry[] table;
    private int size = 16;
    private int threshold = (int) (size * 0.75);
    private int position;

    public SimpleHashSet() {
        this.table = new Entry[size];
    }


    static class Entry<E> {
        int hash;
        E value;
        Entry<E> next;

        public Entry(int hash, E value, Entry<E> next) {
            this.hash = hash;
            this.value = value;
            this.next = next;
        }
    }
    public void put(E value) {
        if (position == threshold) {
            int newSize = size * 2;
            Entry[] newTable = new Entry[newSize];
            transfer(newTable);
            table = newTable;
            size = newSize;
        }
        if (value == null) {
            putForNullKey(value);
        } else {
            int hash = hash(value.hashCode());
            int index = indexFor(hash, size);
            if (table[index] == null) {
                table[index] = new Entry<>(hash, value, null);
                position++;
            } else {
                Entry<E> e = table[index];
                if (!found(e, hash, value)) {
                    table[index] = new Entry<>(hash, value, e);
                    position++;
                }
            }
        }

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
    public void putForNullKey(E value) {
        if (table[0] == null) {
            table[0] = new Entry(0, null, null);
            position++;
        } else {
            Entry<E> e = table[0];
            if (!found(e, 0, null)) {
                table[0] = new Entry<>(0, null, e);
                position++;
            }
        }
    }
    public boolean found(Entry<E> e, int hash, E val ) {
        Entry<E> value = e;
        if (value.hash == hash && (value.value == val || value.value.equals(val))) {
            return  true;
        } else if (value.next != null) {
            value = value.next;
            this.found(value, hash, val);
        }
        return false;
    }
    public void addEntry(int hash, E value, int index) {
        Entry<E> e = table[index];
        table[index] = new Entry(hash, value, e);
    }
    public void transfer(Entry<E>[] newTable) {
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                Entry e = table[i];
                while (true) {

                }
            }
        }
    }
}
