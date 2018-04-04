package ru.job4j.actions;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Process {

    private static final Logger LOG = LogManager.getLogger(Process.class);
    private static final Map<String, Action> ACTIONS = new ConcurrentHashMap<String, Action>() { {
        put("sing", new CreateUser());
        put("login", new UserInOut());
    }
    };

    private Action action;
    private JSONObject jsonObject;

    public void findAction(String request) {
        JSONObject object;
        try {
            object = (JSONObject) new JSONParser().parse(request);
            String action = (String) object.get("action");
            this.action = action == null ? null : ACTIONS.get(action);
            this.jsonObject = (JSONObject) object.get("data");
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String getResponse(HttpSession session) {
        return this.action.action(session, jsonObject);
    }
}
