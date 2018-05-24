package ru.job4j.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.model.Advert;
import ru.job4j.model.usersmodels.User;

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

    private static final Map<String, BiConsumer<StringBuilder, String>> FILTERS = new HashMap<String, BiConsumer<StringBuilder, String>>() { {
        put("brand",        (builder, param) -> builder.append(" and a.car.brand.id =").append(param));
        put("model",        (builder, param) -> builder.append(" and a.car.model.id =").append(param));
        put("engineType",   (builder, param) -> builder.append(" and a.car.engine.fuelType='").append(param).append("'"));
        put("carcass",      (builder, param) -> builder.append(" and a.car.carcass='").append(param).append("'"));
        put("transmission", (builder, param) -> builder.append(" and a.car.transmission='").append(param).append("'"));
        put("gearBox",      (builder, param) -> builder.append(" and a.car.gearBox='").append(param).append("'"));
    } };


    public List<Advert> getAdverts(User user, Map map) {
        List<Advert> adverts = new ArrayList<>();
//        AdvertService service = new AdvertService();
//        if ("byUser".equals(map.get("select"))) {
//            adverts = service.getAdvertsByUser(user);
//        } else {
//            String query = getQueryFilter(map);
//            LOG.info(query);
//            adverts = query.contains("join") ? service.getByQueryWithJoin(query) : service.getByQuery(query);
//        }
        return adverts;
    }

    private String getQueryFilter(Map map) {
        StringBuilder builder = new StringBuilder("from Advert as a");
        if ((boolean) map.remove("image")) {
            builder.append(" join fetch Image as i on i.car.id = a.car.id");
        }
        builder.append(" where a.data ").append((boolean) map.remove("today") ? " >= '" : " <> '")
                .append(FORMAT.format(TODAY)).append("'");
        if (!"all".equals(map.get("select"))) {
            new Filter(builder, map)
                    .addFilterByField("price_from",   "price_to",   " a.price ")
                    .addFilterByField("year_from",    "year_to",    " a.car.year ")
                    .addFilterByField("mileage_from", "mileage_to", "a.car.mileage")
                    .addFilterByField("volume_from",  "volume_to",  "a.car.engine.volume")
                    .addFilters();
        }
        builder.append(" order by a.data DESC");
        return builder.toString();
    }


    class Filter {
        private StringBuilder builder;
        private Map map;

        Filter(StringBuilder builder, Map map) {
            this.builder = builder;
            this.map = map;
        }

       Filter addFilterByField(String from, String to, String field) {
           String jTo = (String) map.remove(to);
           String jFrom = (String) map.remove(from);
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
           for (Object key : map.keySet()) {
               String k = (String) key;
               String v = (String) map.get(k);
               if (FILTERS.get(k) != null) {
                   FILTERS.get(k).accept(builder, v);
               }
           }
       }
    }
}