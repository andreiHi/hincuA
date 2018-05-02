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
        put("volume1", " and a.car.engine.volume < 1000 ");
        put("volume2", " and a.car.engine.volume between 1000 and 2000 ");
        put("volume3", " and a.car.engine.volume between 2000 and 3000 ");
        put("volume4", " and a.car.engine.volume > 3000 ");
        put("mileage1", " and a.car.mileage <100000");
        put("mileage2", " and a.car.mileage between 100000 and 200000 ");
        put("mileage3", " and a.car.mileage between 200000 and 300000 ");
        put("mileage4", " and a.car.mileage between 300000 and 400000 ");
        put("mileage5", " and a.car.mileage > 500000 ");
    } };
    private static final Map<String, BiConsumer<StringBuilder, String>> FILTERS = new HashMap<String, BiConsumer<StringBuilder, String>>() { {
        put("brand",        (builder, param) -> builder.append(" and a.car.brand.id =").append(param));
        put("model",        (builder, param) -> builder.append(" and a.car.model.id =").append(param));
        put("volume",       (builder, param) -> builder.append(getVolume("volume" + param)));
        put("mileage",      (builder, param) -> builder.append(getMileage("mileage" + param)));
        put("engineType",   (builder, param) -> builder.append(" and a.car.engine.fuelType='").append(param).append("'"));
        put("carcass",      (builder, param) -> builder.append(" and a.car.carcass='").append(param).append("'"));
        put("transmission", (builder, param) -> builder.append(" and a.car.transmission='").append(param).append("'"));
        put("gearBox",      (builder, param) -> builder.append(" and a.car.gearBox='").append(param).append("'"));
    } };
    public AdvertSelector() {

    }

    public List<Advert> getAdverts(HttpServletRequest req, JSONObject json) {
        List<Advert> adverts;
        if ("byUser".equals(json.get("select"))) {
            User user = (User) req.getSession().getAttribute("user");
            adverts = new AdvertService().getAdvertsByUser(user);
        } else {
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
            if (!"all".equals(json.get("select"))) {
                addFilters(builder, json);
                addFilterByPrice(builder, json);
                addFilterByYears(builder, json);
            }
            builder.append(" order by a.data DESC");
            String query = builder.toString();
           // System.out.println(query);
            if (query.contains("join")) {
                adverts = new AdvertService().getByQueryWithJoin(query);
            } else {
                adverts = new AdvertService().getByQuery(query);
            }
        }
        return adverts;
    }

    private void addFilters(StringBuilder builder, JSONObject json) {
        Iterator iterator = json.keySet().iterator();
        while (iterator.hasNext()) {
            String k = (String) iterator.next();
            String v = (String) json.get(k);
            if (FILTERS.get(k) != null) {
                FILTERS.get(k).accept(builder, v);
                iterator.remove();
            }
        }
    }

    private void addFilterByPrice(StringBuilder builder, JSONObject json) {
        String priceTo = (String) json.get("price_to");
        String priceFrom = (String) json.get("price_from");
        if (priceFrom != null && priceTo != null) {
            if (Integer.valueOf(priceFrom) < Integer.valueOf(priceTo)) {
                builder.append(" and a.price between ").append(priceFrom).append(" and ").append(priceTo);
            }
            json.remove("price_to");
            json.remove("price_from");

        } else if (priceFrom != null) {
            builder.append(" and a.price >").append(priceFrom);
            json.remove("price_from");
        } else if (priceTo != null) {
            builder.append(" and a.price <").append(priceTo);
            json.remove("price_to");
        }
    }
    private void addFilterByYears(StringBuilder builder, JSONObject json) {
        String yearTo = (String) json.get("year_to");
        String yearFrom = (String) json.get("year_from");
        if (yearTo != null && yearFrom != null) {
            if (Integer.valueOf(yearFrom) < Integer.valueOf(yearTo)) {
                builder.append(" and a.car.year between ").append(yearFrom).append(" and ").append(yearTo);
            }
            json.remove("year_to");
            json.remove("year_from");
        } else if (yearFrom != null) {
            builder.append(" and a.car.year >").append(yearFrom);
            json.remove("year_from");
        } else if (yearTo != null) {
            builder.append(" and a.car.year <").append(yearTo);
            json.remove(yearTo);
        }
    }
    private static String getMileage(String m) {
        return VALUES.get(m);
    }

    private static String getVolume(String v) {
        return VALUES.get(v);
    }

}
