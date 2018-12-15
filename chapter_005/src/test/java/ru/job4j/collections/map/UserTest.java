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
        User user1 = new User("Anna", 28, 1);
        User user2 = new User("Anna", 28, 1);
        Map<User, Integer> map = new HashMap<>();
        map.put(user1, 1);
        map.put(user2, 2);
        System.out.println(map);
    }
}