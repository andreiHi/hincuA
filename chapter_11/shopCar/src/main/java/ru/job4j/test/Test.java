package ru.job4j.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import ru.job4j.model.Advert;
import ru.job4j.service.AdvertService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    private static final Logger LOG = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
AdvertService service = new AdvertService();






    }

    public static <T extends Enum<T>> String getJson(Class<T> tClass) {
        return JSONArray.toJSONString(Arrays.stream(tClass.getEnumConstants()).map(Enum::name)
                .collect(Collectors.toList()));
//        for (Enum<T> tenum : tClass.getEnumConstants()) {
//            list.add(tenum.name());
//        }

    }
    public static <T extends Enum<T>> JSONArray getJson1(Class<T> tClass) {
        JSONArray array = new JSONArray();
        for (Enum<T> tenum : tClass.getEnumConstants()) {
            array.add(tenum.name());
        }
//        array.addAll(Arrays.stream(tClass.getEnumConstants()).map(Enum::name).collect(Collectors.toList()));
        return array;
    }
}
