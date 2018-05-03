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
import java.util.function.BiConsumer;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AdvertSelector {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/YYYY");
    private static final Date TODAY = new Date();
    private static final Logger LOG = LogManager.getLogger(AdvertSelector.class);
    private static final Map<String, String> VALUES = new HashMap<String, String>() { {
        put("volume1",  " and a.car.engine.volume < 1000 ");
        put("volume2",  " and a.car.engine.volume between 1000 and 2000 ");
        put("volume3",  " and a.car.engine.volume between 2000 and 3000 ");
        put("volume4",  " and a.car.engine.volume > 3000 ");
        put("mileage1", " and a.car.mileage <100000");
        put("mileage2", " and a.car.mileage between 100000 and 200000 ");
        put("mileage3", " and a.car.mileage between 200000 and 300000 ");
        put("mileage4", " and a.car.mileage between 300000 and 400000 ");
        put("mileage5", " and a.car.mileage > 500000 ");
    } };
    private static final Map<String, BiConsumer<StringBuilder, String>> FILTERS = new HashMap<String, BiConsumer<StringBuilder, String>>() { {
        put("brand",        (builder, param) -> builder.append(" and a.car.brand.id =").append(param));
        put("model",        (builder, param) -> builder.append(" and a.car.model.id =").append(param));
        put("volume",       (builder, param) -> builder.append(getCondition("volume" + param)));
        put("mileage",      (builder, param) -> builder.append(getCondition("mileage" + param)));
        put("engineType",   (builder, param) -> builder.append(" and a.car.engine.fuelType='").append(param).append("'"));
        put("carcass",      (builder, param) -> builder.append(" and a.car.carcass='").append(param).append("'"));
        put("transmission", (builder, param) -> builder.append(" and a.car.transmission='").append(param).append("'"));
        put("gearBox",      (builder, param) -> builder.append(" and a.car.gearBox='").append(param).append("'"));
    } };


    public List<Advert> getAdverts(HttpServletRequest req, JSONObject json) {
        List<Advert> adverts;
        AdvertService service = new AdvertService();
        if ("byUser".equals(json.get("select"))) {
            User user = (User) req.getSession().getAttribute("user");
            adverts = service.getAdvertsByUser(user);
        } else {
            String query = getQueryFilter(json);
            adverts = query.contains("join") ? service.getByQueryWithJoin(query) : service.getByQuery(query);
        }
        return adverts;
    }

    private String getQueryFilter(JSONObject json) {
        StringBuilder builder = new StringBuilder("from Advert as a");
        if ((boolean) json.remove("image")) {
            builder.append(" join fetch Image as i on i.car.id = a.car.id");
        }
        builder.append(" where a.data ").append((boolean) json.remove("today") ? " >= '" : " <> '")
                .append(FORMAT.format(TODAY)).append("'");
        if (!"all".equals(json.get("select"))) {
            new Filter(builder, json)
                    .addFilterByField("price_from", "price_to", " a.price ")
                    .addFilterByField("year_from", "year_to", " a.car.year ")
                    .addFilters();
        }
        builder.append(" order by a.data DESC");
        return builder.toString();
    }

    private static String getCondition(String m) {
        return VALUES.get(m);
    }

    class Filter {
        private StringBuilder builder;
        private JSONObject json;

        Filter(StringBuilder builder, JSONObject json) {
            this.builder = builder;
            this.json = json;
        }

       Filter addFilterByField(String from, String to, String field) {
           String jTo = (String) json.remove(to);
           String jFrom = (String) json.remove(from);
           if (jTo != null && jFrom != null) {
               if (Integer.valueOf(jFrom) < Integer.valueOf(jTo)) {
                   builder.append(" and ").append(field).append(" between ").append(jFrom).append(" and ").append(jTo);
               }
           } else if (jFrom != null) {
               builder.append(" and ").append(field).append(">").append(jFrom);
           } else if (jTo != null) {
               builder.append(" and ").append(field).append("<").append(jTo);
           }
            return this;
       }

       void addFilters() {
           for (Object key : json.keySet()) {
               String k = (String) key;
               String v = (String) json.get(k);
               if (FILTERS.get(k) != null) {
                   FILTERS.get(k).accept(builder, v);
               }
           }
       }
    }
}