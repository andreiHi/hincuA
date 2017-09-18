package ru.job4j.litle.user;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * SortUser.
 * @author Hincu Andrei (andreih1981@gmail.com) by 18.09.17;
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * Метод возвращает отсортированный сет в порядке увеличения возраста.
     * @param list неотсортированный лист.
     * @return отсортированный сет.
     */
    public Set<User> sort (List<User> list) {
        return new TreeSet<User>(list);
    }
}
