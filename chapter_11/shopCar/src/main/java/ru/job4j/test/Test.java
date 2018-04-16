package ru.job4j.test;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.model.User;

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
        //C %= A, эквивалентно C = C % A
        //System.out.println(sec);
        //   System.out.println(Arrays.toString(Transmission.values()));
        Gson gson = new Gson();
        User user = new User();
        user.setLogin("aaa");
        JSONObject ss = user.toJson();
        System.out.println(ss);
        String s = gson.toJson(user);
        System.out.println(s);
        JSONObject object = new JSONObject();


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
