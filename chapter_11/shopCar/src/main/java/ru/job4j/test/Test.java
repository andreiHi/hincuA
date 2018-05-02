package ru.job4j.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import ru.job4j.actions.AdvertSelector;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    private static final Logger LOG = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
//        SessionFactory service = HibernateService.getSessionFactoty();
//        Session session = service.openSession();

//        AdvertService service = new AdvertService();
//        List<Advert> adverts = service.getByQuery("from Advert as a");
//        adverts.forEach(advert -> System.out.println(advert.toJson()));
//        AdvertService service = new AdvertService();
//        service.getByQueryWithData("from Advert as a join fetch Image as i on i.car = a.car where a.data between :start and :end")
//                .forEach(System.out::println);


AdvertSelector selector = new AdvertSelector();
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
