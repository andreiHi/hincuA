package ru.job4j.actions;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpSession;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Unknown implements Action {

    @Override
    public String action(HttpSession session, JSONObject json) {
        return "";
    }
}
