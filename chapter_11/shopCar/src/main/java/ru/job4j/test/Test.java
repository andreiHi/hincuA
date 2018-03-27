package ru.job4j.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.model.User;
import ru.job4j.service.UserService;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    private static final Logger LOG = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User("andrei", "test", "123456", "78547");
        userService.saveUser(user);
    }
}
