package ru.job4j.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.model.Advert;
import ru.job4j.service.AdvertService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        JSONObject jsonObject = new JSONObject();
        AdvertService service = new AdvertService();
        List<Advert> adverts = service.getAll();
        for (Advert advert : adverts) {
           jsonObject.put(advert.getId(), advert.toJson());

        }
        return jsonObject.toJSONString();
    }
}
