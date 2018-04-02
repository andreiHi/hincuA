package ru.job4j.actions;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Process {
    private Gson gson;
    JSONObject jsonObject;
    private static final Logger LOG = LogManager.getLogger(Process.class);
    private static final Map<String, Action> ACTIONS = new ConcurrentHashMap<String, Action>() { {
        put("create", new CreateUser());
        put("login", new UserInOut());
    }
    };
}
