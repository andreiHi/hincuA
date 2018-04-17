package ru.job4j.actions;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public interface Action {
    String action(HttpServletRequest req, JSONObject json);
}
