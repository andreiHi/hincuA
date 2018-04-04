package ru.job4j.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpSession;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserInOut implements Action {
    private static final Logger LOG = LogManager.getLogger(UserInOut.class);

    @Override
    public String action(HttpSession session, JSONObject json) {
        return null;
    }
}
