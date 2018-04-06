package ru.job4j.actions;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpSession;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 06.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class GetItems implements Action {
    private static final Logger LOG = LogManager.getLogger(GetItems.class);

    @Override
    public String action(HttpSession session, JSONObject json) {
        Gson gson = new Gson();
        return null;
    }
}
