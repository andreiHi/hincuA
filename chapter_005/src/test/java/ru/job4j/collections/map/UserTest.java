package ru.job4j.collections.map;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Тесты.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 15.10.17;
 * @version $Id$
 * @since 0.1
 */
public class UserTest {
    /**
     * Мап.
     */
    private Map<User, Object> map;

    /**
     * Инициализация хранилища.
     */
    @Before
    public void start() {
        map = new HashMap<>();
    }

    /**
     *метод печатает содержимое мап.
     */
    @Test
    public void whenAddedTwoUsersWithOutHashCodeAndEquals() {
        Object o = new Object();
        Object o1 = new Object();
        User user = new User("Petrov", 2);
        User user2 = new User("Petrov", 2);
        map.put(user, o);
        map.put(user2, o1);
        for (User m : map.keySet()) {
            System.out.println(m);

        }
    }
}