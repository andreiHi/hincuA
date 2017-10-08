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
     * @param <E>
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
        public Entry( Entry<E> prev, E element, Entry<E> next) {
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
            int indexPozition;
            /**
             * элемент текущий.
             */
            ContainerAsLinkedList.Entry next = first;

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
                ContainerAsLinkedList.Entry returned;

                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    returned = next;
                    this.next = next.next;
                    indexPozition++;
                }
                return (E) returned.element;
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
        ContainerAsLinkedList.Entry lastElement = this.last;
        ContainerAsLinkedList.Entry elementNew = new ContainerAsLinkedList.Entry(lastElement, element, (ContainerAsLinkedList.Entry)null);
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
        ContainerAsLinkedList.Entry ferst = this.first;
        ContainerAsLinkedList.Entry newElement = new ContainerAsLinkedList.Entry(null, element, ferst);
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
        ContainerAsLinkedList.Entry item = this.first;

        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return (E) item.element;
    }

}
