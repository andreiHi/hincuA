package ru.job4j.collections.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс Set на основе LinkedList.
 * @author Hincu Andrei (andreih1981@gmail.com)on 11.10.2017.
 * @version $Id$.
 * @since 0.1.
 * @param <E> Тип данных.
 */
public class SimpleSetWithLinkedList<E> implements Iterable<E> {
    /**
     * Ссылка на первый элемент.
     */
    private SimpleSetWithLinkedList.Node<E> first;
    /**
     * Ссылка на последний элемент.
     */
    private SimpleSetWithLinkedList.Node<E> last;
    /**
     * Длинна списка.
     */
    private int size = 0;

    /**
     * Конструктор.
     */
    public SimpleSetWithLinkedList() {
    }

    /**
     * Внутренний класс для создания ссылок.
     * @param <E> тип данных.
     */
    private static class Node<E> {
        /**
         * Элемент.
         */
        E element;
        /**
         * ссылка на следующий.
         */
        Node<E> next;
        /**
         * ссылка на предыдущий.
         */
        Node<E> prev;

        /**
         * Конструктор.
         * @param prev ссылка на предыдущий элемент.
         * @param element сам элемент.
         * @param next ссылка на следующий элемент.
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Реализация итератора.
     * @return итератор для списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position = 0;
            private Node<E> elem = first;
            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public E next() {
                Node<E> forReturn;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    forReturn = elem;
                    elem = elem.next;
                    position++;
                }
                return forReturn.element;
            }
        };
    }

    /**
     * Метод добовляет новый уникальный элемент.
     * @param value элемент.
     */
    public void add(E value) {
        boolean found = false;
       Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (value.equals(it.next())) {
                found = true;
                break;
            }
        }
        if (!found) {
            Node<E> last = this.last;
            Node<E> newLast = new Node<>(last, value, null);
            this.last = newLast;
            if (last == null) {
                this.first = newLast;
            } else {
                last.next = newLast;
            }
            size++;
        }
    }
}
