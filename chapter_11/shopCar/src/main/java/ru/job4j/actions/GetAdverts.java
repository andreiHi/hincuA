package ru.job4j.actions;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class GetAdverts implements Action {
    private static final Logger LOG = LogManager.getLogger(GetAdverts.class);

    @Override
    public String action(HttpServletRequest req, JSONObject json) {
        String g = (String) json.get("get");
        System.out.println(json.toJSONString());
        System.out.println(g);
        //Base64.encodeToString
        return new Gson().toJson("advert");
    }
}
