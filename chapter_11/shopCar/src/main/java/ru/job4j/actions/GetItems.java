package ru.job4j.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.model.User;
import ru.job4j.model.car.parts.Carcass;
import ru.job4j.model.car.parts.EngineType;
import ru.job4j.model.car.parts.Gearbox;
import ru.job4j.model.car.parts.Transmission;
import ru.job4j.service.BrandService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 06.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class GetItems implements Action {
    private static final Logger LOG = LogManager.getLogger(GetItems.class);

    @Override  @SuppressWarnings("unchecked")
    public String action(HttpServletRequest req, JSONObject json) {
        HttpSession session = req.getSession();
        JSONObject jsonObject = new JSONObject();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            jsonObject.put("user", user.toJson());
        } else {
            jsonObject.put("user", User.UNKNOWN_USER.toJson());
        }
        jsonObject.put("transmission", getJson(Transmission.class));
        jsonObject.put("carcass",      getJson(Carcass.class));
        jsonObject.put("gearbox",      getJson(Gearbox.class));
        jsonObject.put("engineType",   getJson(EngineType.class));

        String brands = new BrandService().getAllBrandsToJson();

        jsonObject.put("brands", brands);
        //System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }
    @SuppressWarnings("unchecked")
    private <T extends Enum<T>> JSONArray getJson(Class<T> tClass) {
        JSONArray array = new JSONArray();
        for (Enum<T> tenum : tClass.getEnumConstants()) {
            array.add(tenum.name());
        }
        return array;
    }
}
