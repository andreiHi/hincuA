package ru.job4j.collections.generic;

/**
 * Хранилище элементов user .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.10.17;
 * @version $Id$
 * @since 0.1
 * @param <User> параметр.
 */
public class UserStore<User> extends AbstractStore {
    /**
     * Конструктор класса.
     * @param size размер хранилища.
     */
    public UserStore(int size) {
      super(size);
    }
}
