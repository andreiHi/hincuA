package ru.job4j.storage;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public interface Storage<E> {
    long create(E entity);
    E read(long id);
    List<E> getAll();
    boolean update(E entity);
    boolean delete(E entity);
    boolean delete(long id);
}
