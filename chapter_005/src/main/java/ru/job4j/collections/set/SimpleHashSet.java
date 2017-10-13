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
        if (value == null) {
            putForNullKey(value);
        } else {
            int hash = hash(value.hashCode());
            int newIndex = indexFor(hash, size);
            if (table[newIndex] == null) {
                table[newIndex] = new Entry<>(hash, value, null);
            } else {
                Entry e = table[newIndex];
                if (!found(e, hash, value)) {
                    table[newIndex] = new Entry(hash, value, e);
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
}
