package ru.job4j.collections.list;

import java.util.ArrayList;

/**
 * Класс элементов списка.
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.10.2017.
 * @version $Id$.
 * @since 0.1.
 * @param <T> тип даннх.
 *
 */
public class Node<T> {
    /**
     * Значение.
     */
    private   T value;
    /**
     * Ссылка на следующий элемент.
     */
    private Node<T> next;

    /**
     * Конструктор.
     * @param value значение элемента.
     */
    public Node(T value) {
        this.value = value;
    }
    /**
    *Сеттер.
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Класс для цикличности.
     * @param <T> тип данных.
     */
   public static class Cycle<T> {
        /**
         * Метод определяет цикличность в связанном списке.
         * @param first начальный элемент.
         * @return true or false.
         */
        public boolean hasCycle(Node first) {
            ArrayList<Node> list = new ArrayList<>();
            Node<T> element = first;
            boolean found = false;
            while (true) {
                if (element.next == null) {
                    break;
                }
                element = element.next;
                if (list.contains(element)) {
                    found = true;
                    break;
                } else {
                    list.add(element);
                }
            }
            return found;
        }
    }
}
