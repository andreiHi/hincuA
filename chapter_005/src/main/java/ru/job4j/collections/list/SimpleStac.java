package ru.job4j.collections.list;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 09.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleStac<T> extends ContainerAsLinkedList<T> {

    public SimpleStac() {
    }
    public <T> T  poll() {

    }


    public void push(T value) {
        ContainerAsLinkedList.Entry<T> last = getLast();
        ContainerAsLinkedList.Entry<T> newLast = new ContainerAsLinkedList.Entry<T>()

    }
}
