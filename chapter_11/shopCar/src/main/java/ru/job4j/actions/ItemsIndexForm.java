package ru.job4j.actions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.model.car.parts.Carcass;
import ru.job4j.model.car.parts.EngineType;
import ru.job4j.model.car.parts.Gearbox;
import ru.job4j.model.car.parts.Transmission;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 06.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ItemsIndexForm {

    @SuppressWarnings("unchecked")
    public JSONObject setupItems() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transmission", getJson(Transmission.class));
        jsonObject.put("carcass",      getJson(Carcass.class));
        jsonObject.put("gearBox",      getJson(Gearbox.class));
        jsonObject.put("engineType",   getJson(EngineType.class));
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    private <T extends Enum<T>> JSONArray getJson(Class<T> tClass) {
        JSONArray array = new JSONArray();
        for (Enum<T> tEnum : tClass.getEnumConstants()) {
            array.add(tEnum.name());
        }
        return array;
    }
}
