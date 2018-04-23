package ru.job4j.actions;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        put("sing",      new CreateUser());
        put("logInOut",  new UserInOut());
        put("getItems",  new GetItems());
        put("getModels", new GetModels());
        put("unknown",   new Unknown());
        put("create",    new CreateAdvert());
        put("allAds",    new GetAdverts());
        put("setSold",   new CarSold());
    }
    };

    private Action action;
    private JSONObject jsonObject;

    public void findAction(HttpServletRequest req) {
        if (!ServletFileUpload.isMultipartContent(req)) {
            String request;
            JSONObject object;
            try {
                request = req.getReader().readLine();
                object = (JSONObject) new JSONParser().parse(request);
                String action = (String) object.get("action");
                this.action = action == null ? ACTIONS.get("unknown") : ACTIONS.get(action);
                this.jsonObject = (JSONObject) object.get("data");
            } catch (ParseException | IOException e) {
                LOG.error(e.getMessage(), e);
            }
        } else {
           this.action = ACTIONS.get("create");
           this.jsonObject = new JSONObject();
        }
    }

    public String getResponse(HttpServletRequest req) {
        return this.action.action(req, jsonObject);
    }
}
