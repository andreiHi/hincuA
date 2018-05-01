package ru.job4j.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.model.Advert;
import ru.job4j.model.User;
import ru.job4j.service.AdvertService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AdvertSelector {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/YYYY");
    private static final Date TODAY = new Date();
    private static final Logger LOG = LogManager.getLogger(AdvertSelector.class);

    public AdvertSelector() {

    }

    public List<Advert> getAdverts(HttpServletRequest req, JSONObject json) {
        List<Advert> adverts = new ArrayList<>();
        if("byUser".equals(json.get("select"))) {
            User user = (User) req.getSession().getAttribute("user");
            adverts = new AdvertService().getAdvertsByUser(user);
        }
        else {
            StringBuilder builder = new StringBuilder("from Advert as a");
            if ((boolean) json.get("image")) {
                builder.append(" join fetch Image as i on i.car.id = a.car.id");
            }
            json.remove("image");
            if ((boolean) json.get("today")) {
                builder.append(" where a.data  >= '").append(FORMAT.format(TODAY)).append("'");
            } else {
                builder.append(" where a.data  <> '").append(FORMAT.format(TODAY)).append("'");
            }
            json.remove("today");
            String brandId = (String) json.get("brand");
            if (brandId != null) {
                builder.append(" and a.car.brand.id =").append(brandId);
            }
            String modelId = (String) json.get("model");
            if (modelId != null) {
                builder.append(" and a.car.model.id =").append(modelId);
            }
            if (!json.isEmpty()) {
                for (Object key : json.keySet()) {
                    String k = (String) key;
                    String v = (String) json.get(k);
                    System.out.println(k + " " + v);
                }
            }
            builder.append(" order by a.data DESC");
            String query = builder.toString();
            System.out.println(query);
            if (query.contains("join")) {
                adverts = new AdvertService().getByQueryWithJoin(query);
            } else {
                adverts = new AdvertService().getByQuery(query);
            }
        }
        return adverts;
    }


}
