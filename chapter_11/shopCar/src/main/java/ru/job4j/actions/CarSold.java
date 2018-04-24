package ru.job4j.actions;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.service.AdvertService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class CarSold implements Action {
    private static final Logger LOG = LogManager.getLogger(CarSold.class);

    @Override
    public String action(HttpServletRequest req, JSONObject json) {
        String id = (String) json.get("id");
        String state = (String) json.get("state");
        boolean result = new AdvertService().changeState(id, state);
        return new Gson().toJson(result);
    }
}
