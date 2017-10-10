package ru.job4j.collections.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Связанный список.
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.10.17;
 * @version $Id$
 * @param <E> тип данных.
 * @since 0.1
 */
public class ContainerAsLinkedList<E> implements Iterable<E> {
    /**
     * Первый элемент.
     */
    private ContainerAsLinkedList.Entry<E> first;
    /**
     * Последний элемент.
     */
    private ContainerAsLinkedList.Entry<E> last;
    /**
     * Размер списка.
     */
    private int size;

    /**
     * Конструктор.
     */
    public ContainerAsLinkedList() {
        this.size = 0;
    }

    /**
     * Внутренний класс для ссылок на элементы.
     * @param <E> тип данных.
     */
    public static class Entry<E> {
        /**
         * елемент списка.
         */
        E element;
        /**
         * ссылка на следующий.
         */
        Entry<E> next;
        /**
         * ссылка на предыдущий.
         */
        Entry<E> prev;

        /**
         * конструктор.
         * @param prev предыдущий.
         * @param element елемент.
         * @param next следующий.
         */
        public Entry(Entry<E> prev, E element, Entry<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Итератор.
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Текущая позиция.
             */
            private int indexPozition;
            /**
             * элемент текущий.
             */
            private Entry<E> next = first;

            /**
             * есть ли следующий.
             * @return да нет.
             */
            @Override
            public boolean hasNext() {
                return indexPozition < size;
            }

            /**
             * следующий элемент.
             * @return возврат текущего и сдвиг на следующий
             */
            @Override
            public E next() {
                Entry<E> returned;

                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    returned = next;
                    this.next = next.next;
                    indexPozition++;
                }
                return  returned.element;
            }
        };
    }

    /**
     * добавление нового элемента.
     * @param element элемент.
     * @return тру.
     */
    public boolean add(E element) {
        this.addLast(element);
        return true;
    }

    /**
     * Добовление в конец.
     * @param element елемент.
     */
    public void addLast(E element) {
       Entry<E> lastElement = this.last;
       Entry<E> elementNew = new Entry<E>(lastElement, element, null);
        this.last = elementNew;
        if (lastElement == null) {
            this.first = elementNew;
        } else {
            lastElement.next = elementNew;
        }
        size++;
    }

    /**
     * Добавление в начало.
     * @param element елемент.
     */
    public void addFirst(E element) {
        Entry<E> ferst = this.first;
        Entry<E> newElement = new Entry<E>(null, element, ferst);
        this.first = newElement;
        if (ferst == null) {
            this.last = ferst;
        } else {
            ferst.prev = newElement;
        }
        size++;
    }

    /**
     * получение по индексу.
     * @param index индекс.
     * @return елемент.
     */
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Entry<E> item = this.first;

        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return  item.element;
    }

    /**
     * Метод удаляет последний элемент.
     * @return удаленный элемент.
     */
    public E removeLast() {
        Entry<E> last = this.last;
        if (last.prev == null) {
        Entry<E> empty = new Entry<>(null, null, null);
        this.first = empty;
        this.last = empty;
        } else {
            last = this.last;
            Entry<E> newLast = this.last.prev;
            this.last = newLast;
            newLast.next = null;
        }
        this.size--;
        return last.element;
    }

    /**
     * Метод удаляет первый элемент из хранилища.
     * @return первый элемент.
     */
    public E removeFirst() {
        Entry<E> first = this.first;
        if (first.next == null) {
            Entry<E> empty = new Entry<>(null, null, null);
            this.first = empty;
            this.last = empty;
        } else {
            Entry<E> newFirst = first.next;
            newFirst.prev = null;
            this.first = newFirst;

        }
        this.size--;
        return first.element;
    }
    /**
     * Размер хранилища.
     * @return число.
     */
    public int getSize() {
        return size;
    }

}
