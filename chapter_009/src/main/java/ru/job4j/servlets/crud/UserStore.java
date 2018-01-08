package ru.job4j.servlets.crud;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserStore {

    private static final Logger LOG = LogManager.getLogger(UserStore.class);
    private static final UserStore instance = new UserStore();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return instance;
    }
}
