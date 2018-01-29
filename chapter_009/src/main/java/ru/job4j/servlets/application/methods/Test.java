package ru.job4j.servlets.application.methods;

import ru.job4j.servlets.crud.User;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 29.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    public static void main(String[] args) {
        User user = new User("root", null, null, "root");
        System.out.println(user.getLogin().equals("root") && user.getPassword().equals("rot"));
        System.out.println(user);
    }
}
