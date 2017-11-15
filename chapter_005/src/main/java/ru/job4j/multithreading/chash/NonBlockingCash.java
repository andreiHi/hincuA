package ru.job4j.multithreading.chash;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 15.11.17;
 * @version $Id$
 * @since 0.1
 */
public class NonBlockingCash {
    private ConcurrentHashMap<Integer, User> map = new ConcurrentHashMap<>();
    public void add(User user) {
        map.putIfAbsent(user.getId(), user);
    }

    public void delete (User user) {
        map.remove(user.getId());
    }
    public void update (User user) {
        int v = user.getVersion();
        map.computeIfPresent(user.getId(), new BiFunction<Integer, User, User>() {
            @Override
            public User apply(Integer integer, User user) {
                if ((v + 1) == integer) {
                    throw new RuntimeException();
                }
                user.setVersion(v + 1);
                return user;
            }
        });
    }
}
class Calculator {
    public String calc(BiFunction<Integer, Integer, String> bi, Integer i1, Integer i2) {
        return bi.apply(i1, i2);
    }
}
class Main {
    /*from  w  w  w. j a  v a  2 s  . c  o m*/
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String result = calculator.calc((a, b) -> ": " + (a + b),3,  5);
        System.out.println(result);
    }
}
